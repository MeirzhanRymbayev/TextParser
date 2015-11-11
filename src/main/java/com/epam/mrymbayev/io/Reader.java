package com.epam.mrymbayev.io;

import com.epam.mrymbayev.io.exception.ReadingException;
import java.io.FileNotFoundException;

/**
 * Reader interface
 * @author Rymbayev Meirzhan
 * @version 1.0
 * Created by Meir on 09.11.2015.
 */
public interface Reader {
    String getFullText(String path) throws FileNotFoundException, ReadingException;
}
