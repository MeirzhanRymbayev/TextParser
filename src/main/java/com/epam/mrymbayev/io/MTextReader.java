package com.epam.mrymbayev.io;

import com.epam.mrymbayev.io.exception.ReadingException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Meir on 09.11.2015.
 */
public class MTextReader implements Reader {
    Logger ioLog = Logger.getLogger(MTextReader.class);

    @Override
    public String getFullText(final String path) throws ReadingException {
        ioLog.info("Try to find \"" + path + "\" file to read.");
        //System.out.println("Reading file \"" + path +"\"...");
        StringBuilder fullText = new StringBuilder();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path)).useDelimiter("\\Z");
            ioLog.info("File found.");
            ioLog.info("Reading file...");
            while(scanner.hasNextLine()){
                fullText.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            ioLog.error("File " + path + " not found. Please, check path to file.");
            throw new ReadingException("File " + path + " found. Please, use correct path to file.");
            } finally {
            scanner.close();
        }
        ioLog.info("Reading finish success.");
        return fullText.toString();

    }
}
