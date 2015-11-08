package com.epam.mrymbayev;

import com.epam.mrymbayev.reader.FileReader;
import com.epam.mrymbayev.reader.Reader;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        FileReader reader = new FileReader();
        String s = reader.getFullText("D:/File1.txt");
        System.out.println(s);
    }
}
