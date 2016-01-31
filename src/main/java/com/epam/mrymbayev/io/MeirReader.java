package com.epam.mrymbayev.io;

import com.epam.mrymbayev.io.exception.ReadingException;
import org.apache.log4j.Logger;

import java.io.*;
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
        ioLog.info("Hello! This is text parser lib. ");
        StringBuilder fullText = new StringBuilder();
        Scanner scanner = null;
        ioLog.info("Try to find \"" + path + "\" file to read.");
        try {
            scanner = new Scanner(new File(path)).useDelimiter("\\Z");
            ioLog.info("File was found.");
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

    public String getPathFromConsole(String inputOrOutputFilePath) {
        String pathToFile = null;
        InputStream inputStream = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            System.out.println("Please type path to "+ inputOrOutputFilePath +" file...");
            try {
                pathToFile = bufferedReader.readLine();
            } catch (IOException e) {
                ioLog.error("Incorrect path to file", e);
                e.printStackTrace();
            }
        return pathToFile;
    }
}
