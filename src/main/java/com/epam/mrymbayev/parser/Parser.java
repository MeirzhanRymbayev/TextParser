package com.epam.mrymbayev.parser;

import com.epam.mrymbayev.entity.Composite;
import com.epam.mrymbayev.parser.exception.ParseException;
import com.epam.mrymbayev.parser.exception.PropertyFilePathException;

/**
 * Parser interface
 * @author Rymbayev Meirzhan
 * @version 1.0
 * Created by Meir on 08.11.2015.
 */
public interface Parser {

    <T extends Composite> T parse(String sourceString, Class<T> compositeClass)
                                throws ParseException, PropertyFilePathException;

    <T extends Composite> T parse(String sourceString) throws ParseException, PropertyFilePathException;

}
