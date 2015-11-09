package com.epam.mrymbayev.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Meir on 09.11.2015.
 */
public class TFileReader implements Reader {
    @Override
    public String getFullText(String path) {
        StringBuilder fullText = new StringBuilder();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path)).useDelimiter("\\Z");
            while(scanner.hasNextLine()){
                fullText.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
        return fullText.toString();

    }
}
