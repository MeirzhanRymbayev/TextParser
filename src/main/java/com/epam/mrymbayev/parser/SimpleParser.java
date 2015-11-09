package com.epam.mrymbayev.parser;

import com.epam.mrymbayev.entity.*;
import com.epam.mrymbayev.entity.Number;
import com.epam.mrymbayev.parser.exception.ParseException;
import com.epam.mrymbayev.parser.exception.PropertyFilePathException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Meir on 08.11.2015.
 */
public class SimpleParser implements Parser {

    private Map<Class<? extends Composite>, String> patternsMap;  //Map for define kind of regex for current Class
    private Map<Class<? extends Composite>, Class<? extends Component>> componentMap;
    private Properties parserProps; //All properties was loaded

    public SimpleParser() throws PropertyFilePathException {
        this.parserProps = getParserProps("parser.properties");
        this.componentMap = setComponentMap();
        this.patternsMap = setPatternsMap();
    }

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
            throw new PropertyFilePathException("Invalid path to property file :" + parserProperties);
        }
        return properties;
    }

    private Class<? extends Composite> getClassForFirstMapParam(String parserPropFileKey) throws PropertyFilePathException {
        Class<? extends Composite> compositeClass = null;
        try {
            compositeClass = (Class<? extends Composite>) Class.forName(parserProps.getProperty(parserPropFileKey));
        } catch (ClassNotFoundException e) {
            throw new PropertyFilePathException("Invalid String key in property file");
        }
        return compositeClass;
    }

    private Class<? extends Component> getClassForSecondMapParam(String parserPropFileKey) throws PropertyFilePathException {
        Class<? extends Component> componentClass = null;
        try {
            componentClass = (Class<? extends Component>) Class.forName(parserProps.getProperty(parserPropFileKey));
        } catch (ClassNotFoundException e) {
            throw new PropertyFilePathException("Invalid String key in property file");
        }
        return componentClass;
    }



    public Text parse(String s) throws ParseException, PropertyFilePathException {
        return parse(s, Text.class);
    }

    @Override
    public <T extends Composite> T parse(String sourceString, Class<T> compositeClass)
                                                        throws ParseException, PropertyFilePathException {

        final String IS_WORD = parserProps.getProperty("word");
        final String IS_NUMBER = parserProps.getProperty("number");
        final String IS_PMARK = parserProps.getProperty("punctuation");

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
                    Component c;
                    String matchedString = matches.group();
                        if (matchedString.matches(IS_WORD)) {
                            c = parse(matchedString, Word.class);
                        } else if (matchedString.matches(IS_PMARK)) {
                            c = parse(matchedString, PMark.class);
                        } else if (matchedString.matches(IS_NUMBER)) {
                            c = parse(matchedString, Number.class);
                        } else {
                            c = parse(matchedString, UnknownToken.class);
                        }
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
        } catch (Exception e){
            throw new ParseException("Error in parsing method parse()");
        }


    }


}
