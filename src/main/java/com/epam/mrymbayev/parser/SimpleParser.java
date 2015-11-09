package com.epam.mrymbayev.parser;

import com.epam.mrymbayev.entity.*;
import com.epam.mrymbayev.entity.Number;
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

    private Map<Class<? extends Composite>, String> patterns;  //Map for define kind of regex for current Class
    private Map<Class<? extends Composite>, Class<? extends Component>> componentMap;
    Properties parserProps;

    private final String PARAGR_IN_TEXT_REGEX = parserProps.getProperty("paragraph");
    private final String SENTNC_IN_PARAGR = parserProps.getProperty("sentence");
    private final String WORD_IN_SENTNC = parserProps.getProperty("word");
    private final String NUMB_IN_SENTNC = parserProps.getProperty("number");
    private final String PMARK_IN_SENTNC = parserProps.getProperty("punctuation");


    private Properties getParserProps(String parserProperties) throws PropertyFilePathException {
        Properties properties = new Properties();
        try (InputStream in = Parser.class.getClassLoader().getResourceAsStream(parserProperties)) {
            properties.load(in);
        } catch (IOException e) {
            throw new PropertyFilePathException("Invalid path to property file :" + parserProperties);
        }
        return properties;
    }

    private Class<? extends Composite> getClassForFirstMapParam(String parserPropFileKey) {
        Class<? extends Composite> compositeClass = null;
        try {
            compositeClass = (Class<? extends Composite>) Class.forName(parserProps.getProperty(parserPropFileKey));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return compositeClass;
    }

    private Class<? extends Component> getClassForSecondMapParam(String parserPropFileKey) {
        Class<? extends Component> componentClass = null;
        try {
            componentClass = (Class<? extends Component>) Class.forName(parserProps.getProperty(parserPropFileKey));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return componentClass;
    }


    private Map<Class<? extends Composite>, Class<? extends Component>> setComponentMap() {
        componentMap = new HashMap<>();
        componentMap.put(getClassForFirstMapParam("text.class"), getClassForSecondMapParam("text.components"));
        componentMap.put(getClassForFirstMapParam("text.components"), getClassForSecondMapParam("paragraph.components"));
        componentMap.put(getClassForFirstMapParam("paragraph.components"), getClassForSecondMapParam("sentence.components"));
        componentMap.put(Number.class, Symbol.class);
        componentMap.put(Word.class, Symbol.class);
        componentMap.put(PMark.class, Symbol.class);
        componentMap.put(UnknownToken.class, Symbol.class);
        return componentMap;
    }

    public Text parse(String s) throws PropertyFilePathException {
        return parse(s, Text.class);
    }

    @Override
    public <T extends Composite> T parse(String sourceString, Class<T> compositeClass) throws PropertyFilePathException {

        parserProps = getParserProps("parser.properties"); //All properties was loaded

        componentMap = setComponentMap();

        T t;
        String regexForComposite;

        try {
            t = compositeClass.newInstance();  // map(Key = compositeClass, Value = componentClass)
            regexForComposite = t.getClass().getName(); //Возвращ String подходит чтобы исполь как ключ в pattern Map-е
            Class componentClass; //map(compositeClass, componentClass);

            if ((compositeClass == Word.class) || (compositeClass == PMark.class) ||
                    (compositeClass == Number.class)) {
                componentClass = Symbol.class;
            } else {
                componentClass = componentMap.get(compositeClass);
            }
            Pattern p = Pattern.compile(parserProps.getProperty(regexForComposite));
            Matcher matches = p.matcher(sourceString);
            if (compositeClass == Sentence.class) {
                while (matches.find()) {
                    String matchedString = matches.group();
                    if (matchedString.matches(WORD_IN_SENTNC)) {
                        Component c = parse(matchedString, Word.class);
                        t.add(c);
                    } else if (matchedString.matches(PMARK_IN_SENTNC)) {
                        Component c = parse(matchedString, PMark.class);
                        t.add(c);
                    } else if (matchedString.matches(NUMB_IN_SENTNC)) {
                        Component c = parse(matchedString, Number.class);
                        t.add(c);
                    } else {
                        Component c = parse(matchedString, UnknownToken.class);
                        t.add(c);
                    }

                }
            } else {
                while (matches.find()) {
                    String matchedString = matches.group();
                    if (componentClass == Symbol.class) {
                        Symbol s = new Symbol(matchedString);
                        t.add(s);
                    } else {
                        Component c = parse(matchedString, componentClass);
                        t.add(c); //map(t = composite, c = component)
                    }
                }
            }

            return t;

        } catch (InstantiationException | IllegalAccessException ignored) {
            throw new RuntimeException();
        }


    }


}
