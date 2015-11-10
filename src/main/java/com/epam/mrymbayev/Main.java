package com.epam.mrymbayev;

import com.epam.mrymbayev.entity.Component;
import com.epam.mrymbayev.entity.Text;
import com.epam.mrymbayev.io.exception.ReadingException;
import com.epam.mrymbayev.parser.SimpleParser;
import com.epam.mrymbayev.io.MTextReader;
import com.epam.mrymbayev.parser.exception.ParseException;
import com.epam.mrymbayev.parser.exception.PropertyFilePathException;
import org.apache.log4j.Logger;

/**
 * Hello world!
 */
public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

        public static void main(String[] args) throws  ReadingException, PropertyFilePathException, ParseException {

        MTextReader reader = new MTextReader();
        String s = reader.getFullText("src/main/resources/File1.txt");
        SimpleParser simpleParser = new SimpleParser();
        Text parsedText = simpleParser.parse(s);


        String text = parsedText.toSourceString();
        log.info("Start to compare TWO text instances");

    }
}
