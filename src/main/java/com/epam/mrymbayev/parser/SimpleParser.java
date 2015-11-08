package com.epam.mrymbayev.parser;

import com.epam.mrymbayev.entity.*;

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
    Properties parserProps = getParserProps("parser.properties"); //All properties was loaded

    private final String PARAGR_IN_TEXT_REGEX = parserProps.getProperty("paragraph");
    private final String SENTNC_IN_PARAGR = parserProps.getProperty("sentence");
    private final String WORD_IN_SENTNC = parserProps.getProperty("word");
    private final String NUMB_IN_SENTNC = parserProps.getProperty("number");
    private final String PMARK_IN_SENTNC = parserProps.getProperty("punctuation");
    private final String SPACE = parserProps.getProperty("space");


    private Properties getParserProps(String parserProperties) {
        Properties properties = new Properties();
            try (InputStream in = Parser.class.getClassLoader().getResourceAsStream(parserProperties)) {
                properties.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return properties;
    }

    private Class<? extends Composite> getClassForFirstMapParam(String parserPropFileKey){
        Class<? extends Composite> compositeClass = null;
        try {
            compositeClass = (Class<? extends Composite>) Class.forName(parserProps.getProperty(parserPropFileKey));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return compositeClass;
    }

    private Class<? extends Component> getClassForSecondMapParam(String parserPropFileKey){
        Class<? extends Component> componentClass = null;
        try {
            componentClass = (Class<? extends Component>) Class.forName(parserProps.getProperty(parserPropFileKey));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return componentClass;
    }


    private Map<Class<? extends Composite>, Class<? extends Component>> setComponentMap(){
        componentMap = new HashMap<>();
        componentMap.put(getClassForFirstMapParam("text.class"), getClassForSecondMapParam("text.components"));
        componentMap.put(getClassForFirstMapParam("text.components"), getClassForSecondMapParam("paragraph.components"));
        componentMap.put(getClassForFirstMapParam("paragraph.components"), getClassForSecondMapParam("sentence.components"));
        componentMap.put(getClassForFirstMapParam("sentence.components"), getClassForSecondMapParam("sentenceToken.components"));
        return componentMap;
    }



    @Override
    public <T extends Composite> T parse(String sourceString, Class<T> compositeClass) {

        componentMap = setComponentMap();

        T t;
        String keyForRegexProp;

        try {
            t = compositeClass.newInstance();
            Class componentClass = componentMap.get(compositeClass);
            keyForRegexProp = t.getClass().getName(); //Возвращ String подходит чтобы исполь как ключ в pattern Map-е

            Pattern p = Pattern.compile(parserProps.getProperty(keyForRegexProp));
            Matcher matches = p.matcher(sourceString);
            while(matches.find()){
                if(componentClass == SentenceToken.class){
                    String matchedString = matches.group();
                    if(WORD_IN_SENTNC.matches(matchedString)){
                        parse(matchedString, componentClass);
                    } else if(PMARK_IN_SENTNC.matches(matchedString)){
                        parse(matchedString, componentClass);
                    } else if(NUMB_IN_SENTNC.matches(matchedString)){
                        parse(matchedString, componentClass);
                    } else if(SPACE.matches(matchedString)){
                        parse(matchedString, componentClass);
                    }
                }


                String string = matches.group();
                if(componentClass == Symbol.class){
                    Symbol s = new Symbol(string);
                    t.add(s);
                } else {
                    Component c = parse(string, componentClass);
                    t.add(c);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
