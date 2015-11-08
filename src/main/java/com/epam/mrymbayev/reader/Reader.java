package com.epam.mrymbayev.reader;

import java.io.FileNotFoundException;

/**
 * Created by Meir on 09.11.2015.
 */
public interface Reader {
    String getFullText(String path) throws FileNotFoundException;
}
