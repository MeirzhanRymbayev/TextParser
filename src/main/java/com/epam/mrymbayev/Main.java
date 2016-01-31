package com.epam.mrymbayev;

import com.epam.mrymbayev.entity.Text;
import com.epam.mrymbayev.entity.Word;
import com.epam.mrymbayev.io.MeirReader;
import com.epam.mrymbayev.io.Reader;
import com.epam.mrymbayev.io.exception.ReadingException;
import com.epam.mrymbayev.parser.Parser;
import com.epam.mrymbayev.parser.SimpleParser;
import com.epam.mrymbayev.parser.exception.ParseException;
import com.epam.mrymbayev.parser.exception.PropertyFilePathException;
import com.epam.mrymbayev.task.SpringInterviewTask;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Main class
 *
 * @author Rymbayev Meirzhan
 * @version 1.0
 */
public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) throws ReadingException, PropertyFilePathException, ParseException, FileNotFoundException {
        Reader reader = new MeirReader();
        log.info("MeirReader instance was created.");
        String pathToInputFile = reader.getPathFromConsole("input");
        String originalText = reader.getFullText(pathToInputFile);

        Parser simpleParser = new SimpleParser();
        log.info("SimpleParser instance was created.");
        Text parsedText = simpleParser.parse(originalText);

        List<Word> components = parsedText.getComponentsByClass(Word.class, new ArrayList());

        SpringInterviewTask task = new SpringInterviewTask();
        Map<Word, Integer> wordsOftenCount = task.getWordsOftenCount(parsedText);

        String pathToOutputFile = reader.getPathFromConsole("output");

        task.writeToFile(wordsOftenCount.toString(), pathToOutputFile);


    }
}
