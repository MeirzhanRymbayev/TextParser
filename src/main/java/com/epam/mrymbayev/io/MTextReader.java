package com.epam.mrymbayev.io;

import com.epam.mrymbayev.io.exception.ReadingException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Meir on 09.11.2015.
 */
public class MTextReader implements Reader {
    @Override
    public String getFullText(String path) throws ReadingException {
        System.out.println("Reading file:" + path +"...");
        StringBuilder fullText = new StringBuilder();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path)).useDelimiter("\\Z");
            while(scanner.hasNextLine()){
                fullText.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            throw new ReadingException("File " + path + "not found. Please, use correct path to file.");
            } finally {
            scanner.close();
        }
        System.out.println("Reading finished...");
        return fullText.toString();

    }
}
