package com.epam.mrymbayev.parser;

import com.epam.mrymbayev.entity.*;
import com.epam.mrymbayev.entity.Number;
import com.epam.mrymbayev.parser.exception.ParseException;
import com.epam.mrymbayev.parser.exception.PropertyFilePathException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SimpleParser class has method parse() which parse passed sourceString parameter
 * into Words, PMarks, Numbers, Sentences, Paragraphs, Text classes.
 *
 * @author Rymbayev Meirzhan
 * @version 1.0
 * @see Parser
 */
public class SimpleParser implements Parser {
    private static final Logger log = Logger.getLogger(SimpleParser.class);

    private Map<Class<? extends Composite>, String> patternsMap;  //Map for define kind of regex for current Class
    private Map<Class<? extends Composite>, Class<? extends Component>> componentMap;
    private Properties parserProps;

    public SimpleParser() throws PropertyFilePathException {
        log.info("Created new instance of SimpleParser");
        this.parserProps = getParserProps("parser.properties");
        this.componentMap = setComponentMap();
        this.patternsMap = setPatternsMap();
        log.info("SimpleParser fields was initialized.");
    }

    /**
     * Method set componentMap field of SimpleParser instance
     *
     * @return Map of text entity pairs. componentMap(Text.class, Paragraph.class)... etc.
     * @throws PropertyFilePathException
     */
    private Map<Class<? extends Composite>, Class<? extends Component>> setComponentMap() throws PropertyFilePathException {
        componentMap = new HashMap<>();
        componentMap.put(getClassForFirstMapParam("text.class"), getClassForSecondMapParam("paragraph.class"));
        componentMap.put(getClassForFirstMapParam("paragraph.class"), getClassForSecondMapParam("sentence.class"));
        componentMap.put(getClassForFirstMapParam("sentence.class"), getClassForSecondMapParam("sentence.components"));
        componentMap.put(getClassForFirstMapParam("number.class"), getClassForSecondMapParam("symbol.class"));
        componentMap.put(getClassForFirstMapParam("word.class"), getClassForSecondMapParam("symbol.class"));
        componentMap.put(getClassForFirstMapParam("pmark.class"), getClassForSecondMapParam("symbol.class"));
        componentMap.put(getClassForFirstMapParam("unknowntoken.class"), getClassForSecondMapParam("symbol.class"));
        return componentMap;
    }

    /**
     * Method set patternsMap field of SimpleParser instance
     *
     * @return Map of text entity pairs. componentMap(Text.class, String regexForSplitIntoParagraph)... etc.
     * @throws PropertyFilePathException
     */
    private Map<Class<? extends Composite>, String> setPatternsMap() throws PropertyFilePathException {
        patternsMap = new HashMap<>();
        patternsMap.put(getClassForFirstMapParam("text.class"), parserProps.getProperty("textToParagrRegex"));
        patternsMap.put(getClassForFirstMapParam("paragraph.class"), parserProps.getProperty("paragrToSentenRegex"));
        patternsMap.put(getClassForFirstMapParam("sentence.class"), parserProps.getProperty("sentncToTokensRegex"));
        patternsMap.put(getClassForFirstMapParam("word.class"), parserProps.getProperty("wordToSymbRegex"));
        patternsMap.put(getClassForFirstMapParam("pmark.class"), parserProps.getProperty("pmarkToSymbRegex"));
        patternsMap.put(getClassForFirstMapParam("number.class"), parserProps.getProperty("numbToSymbRegex"));
        return patternsMap;
    }

    private Properties getParserProps(String parserProperties) throws PropertyFilePathException {
        Properties properties = new Properties();
        try (InputStream in = Parser.class.getClassLoader().getResourceAsStream(parserProperties)) {
            properties.load(in);
        } catch (IOException e) {
            log.error("Invalid path to property file :" + parserProperties);
            throw new PropertyFilePathException("Invalid path to property file :" + parserProperties);
        }
        return properties;
    }

    /**
     * Method returns Key for componentMap, patternsMap from parser.properties
     *
     * @return componentMap key. For Example, Text.class (just ? extends Composite elements)
     * @throws PropertyFilePathException
     * @see SimpleParser methods setPatternsMap(), setComponentMap()
     */
    private Class<? extends Composite> getClassForFirstMapParam(String parserPropFileKey) throws PropertyFilePathException {
        Class<? extends Composite> compositeClass = null;
        try {
            compositeClass = (Class<? extends Composite>) Class.forName(parserProps.getProperty(parserPropFileKey));
        } catch (ClassNotFoundException e) {
            throw new PropertyFilePathException("Invalid String key in property file");
        }
        return compositeClass;
    }

    /**
     * Method returns Value for componentMap from parser.properties
     *
     * @return componentMap Value. For Example, Paragraph.class (just ? extends Component elements)
     * @throws PropertyFilePathException
     * @see SimpleParser methods setPatternsMap()
     */
    private Class<? extends Component> getClassForSecondMapParam(String parserPropFileKey) throws PropertyFilePathException {
        Class<? extends Component> componentClass = null;
        try {
            componentClass = (Class<? extends Component>) Class.forName(parserProps.getProperty(parserPropFileKey));
        } catch (ClassNotFoundException e) {
            throw new PropertyFilePathException("Invalid String key in property file");
        }
        return componentClass;
    }

    /**
     * Overload parse(String sourceString, Class<T> compositeClass) method.
     * Contribute to make it easy method call.
     *
     * @param sourceString String which we need to parse
     * @return <T extends Composite> T parsed instance.
     * @throws ParseException
     * @throws PropertyFilePathException
     * @see SimpleParser parse(String sourceString, Class<T> compositeClass) method.
     */
    public Text parse(String sourceString) throws ParseException, PropertyFilePathException {
        return parse(sourceString, Text.class);
    }

    /**
     * Recursively method to parse sourceString parameter.
     *
     * @param sourceString String which we need to parse
     * @param <T>          compositeClass Class which we need to get
     * @return <T extends Composite> T parsed instance.
     * @throws ParseException
     */
    @Override
    public <T extends Composite> T parse(String sourceString, Class<T> compositeClass)
            throws ParseException{


        T t;
        String regexTSplit;

        try {
            t = compositeClass.newInstance();  // map(Key = compositeClass, Value = componentClass)
            regexTSplit = patternsMap.get(t.getClass()); //Возвращ String подходит чтобы исполь как ключ в pattern Map-е
            Class componentClass; //map(compositeClass, componentClass);

            if ((compositeClass == Word.class) || (compositeClass == PMark.class) ||
                    (compositeClass == Number.class)) {
                componentClass = Symbol.class;
            } else {
                componentClass = componentMap.get(compositeClass);
            }
            Pattern p = Pattern.compile(regexTSplit);
            Matcher matches = p.matcher(sourceString);
            if (compositeClass == Sentence.class) {
                while (matches.find()) {
                    String matchedString = matches.group();
                    Component c = defineSentenceTokenType(matchedString);
                    t.add(c);
                }
            } else {
                while (matches.find()) {
                    String matchedString = matches.group();
                    if (componentClass == Symbol.class) {
                        Symbol s = new Symbol(matchedString);
                        t.add(s);
                    } else {
                        Component c = parse(matchedString, componentClass);
                        t.add(c);
                    }
                }
            }

            return t;

        } catch (InstantiationException | IllegalAccessException ignored) {
            throw new RuntimeException();
        } catch (Exception e) {
            throw new ParseException("Error in parsing method parse()");
        }


    }

    /**
     * Method defines sentence token types e.g. Word, Number, Punctuation mark or Unknown token
     * @param matchedString String object, from where tokens will be searched.
     * @return Component type subclass e.g. Word, Pmark, Number type.
     * @throws PropertyFilePathException
     * @throws ParseException
     */
    private Component defineSentenceTokenType(String matchedString) throws PropertyFilePathException, ParseException {

        final String IS_WORD = parserProps.getProperty("word");
        final String IS_NUMBER = parserProps.getProperty("number");
        final String IS_PMARK = parserProps.getProperty("punctuation");

        Component c;
        if (matchedString.matches(IS_WORD)) {
            c = parse(matchedString, Word.class);
        } else if (matchedString.matches(IS_PMARK)) {
            c = parse(matchedString, PMark.class);
        } else if (matchedString.matches(IS_NUMBER)) {
            c = parse(matchedString, Number.class);
        } else {
            c = parse(matchedString, UnknownToken.class);
        }
        return c;
    }


}
