package com.epam.mrymbayev.parser;

import com.epam.mrymbayev.entity.Composite;
import com.epam.mrymbayev.parser.exception.ParseException;
import com.epam.mrymbayev.parser.exception.PropertyFilePathException;

/**
 * Created by Meir on 08.11.2015.
 */
public interface Parser {

    <T extends Composite> T parse(String sourceString, Class<T> compositeClass)
                                throws ParseException, PropertyFilePathException;

}
