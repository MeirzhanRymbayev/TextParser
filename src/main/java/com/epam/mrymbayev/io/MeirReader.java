package com.epam.mrymbayev.io;

import com.epam.mrymbayev.io.exception.ReadingException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * MeirReader class
 * @author Rymbayev Meirzhan
 * @version 1.0
 * @see Reader
 */
public class MeirReader implements Reader {
    Logger ioLog = Logger.getLogger(MeirReader.class);

    @Override
    public String getFullText(final String path) throws ReadingException {
        ioLog.info("Hello! This is text parser lib. \n Task number is 5. \n We need replace first and last words " +
                "in sentences each other. \n Path to file get in Main.class" );
        StringBuilder fullText = new StringBuilder();
        Scanner scanner = null;
        ioLog.info("Try to find \"" + path + "\" file to read.");
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
        ioLog.info("Reading finish successfully.");
        return fullText.toString();

    }
}
