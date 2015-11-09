package com.epam.mrymbayev.io;

import com.epam.mrymbayev.io.exception.ReadingException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Meir on 09.11.2015.
 */
public class TFileReader implements Reader {
    @Override
    public String getFullText(String path) throws ReadingException {

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
        return fullText.toString();

    }
}
