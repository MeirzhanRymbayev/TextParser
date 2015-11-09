package com.epam.mrymbayev;

import com.epam.mrymbayev.entity.Text;
import com.epam.mrymbayev.parser.SimpleParser;
import com.epam.mrymbayev.reader.TFileReader;

import java.io.FileNotFoundException;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        TFileReader reader = new TFileReader();
        String s = reader.getFullText("D:/File1.txt");
        //System.out.println(s);

        SimpleParser simpleParser = new SimpleParser();
        simpleParser.parse(s);
    }
}
