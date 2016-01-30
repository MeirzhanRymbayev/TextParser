package com.epam.mrymbayev;
/*Task2
Создать программу обработки текста учебника по программированию с использованием классов:
Символ, Слово, Предложение, Знак препинания и др.
Во всех задачах с формированием текста заменять табуляции и последовательности пробелов одним пробелом.

5.	В каждом предложении текста поменять местами первое слово с последним, не изменяя длины предложения.
*/

import com.epam.mrymbayev.entity.*;
import com.epam.mrymbayev.io.MeirReader;
import com.epam.mrymbayev.io.Reader;
import com.epam.mrymbayev.io.exception.ReadingException;
import com.epam.mrymbayev.parser.Parser;
import com.epam.mrymbayev.parser.SimpleParser;
import com.epam.mrymbayev.parser.exception.ParseException;
import com.epam.mrymbayev.parser.exception.PropertyFilePathException;
import com.epam.mrymbayev.task.SpringInterviewTask;
import com.epam.mrymbayev.task.Task;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Main class
 * @author Rymbayev Meirzhan
 * @version 1.0
 */
public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) throws ReadingException, PropertyFilePathException, ParseException, FileNotFoundException {


        Reader reader = new MeirReader();
        log.info("MeirReader instance was created.");
        String originalText = reader.getFullText("src/main/resources/File1.txt");

        Parser simpleParser = new SimpleParser();
        log.info("SimpleParser instance was created.");
        Text parsedText = simpleParser.parse(originalText);

        List<Word> components = parsedText.getClazzComponents(Word.class, new ArrayList());
//        Word word1 = components.get(1);
//        Word word2 = components.get(2);
//        boolean isEquals = word1.equals(word2);
//        System.out.println(isEquals);


        SpringInterviewTask task = new SpringInterviewTask();
        Map<Word, Integer> wordsOftenCount = task.getWordsOftenCount(parsedText);
//        System.out.println(wordsOftenCount.toString());


        task.writeToFile(wordsOftenCount.toString(), "D:/File2.txt");

        /*Task myTask = new Task5();
        Text text = myTask.execute(parsedText);
        //System.out.println(text.toSourceString());*/



    }
}
