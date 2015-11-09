package com.epam.mrymbayev;

import com.epam.mrymbayev.entity.PMark;
import com.epam.mrymbayev.entity.Symbol;
import com.epam.mrymbayev.reader.TFileReader;

import java.io.FileNotFoundException;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        TFileReader reader = new TFileReader();
        String s = reader.getFullText("src/main/resources/File1.txt");

        //SimpleParser simpleParser = new SimpleParser();
        //simpleParser.parse(s);

        PMark pMark = new PMark();
        Symbol symbol = new Symbol("s");
        System.out.println("Symbol: " + symbol.toSourceString());
        pMark.add(symbol);
        System.out.println(pMark.toSourceString());
    }
}
