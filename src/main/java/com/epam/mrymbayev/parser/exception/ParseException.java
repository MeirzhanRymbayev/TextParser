package com.epam.mrymbayev.parser.exception;

/**
 * Created by Meir on 08.11.2015.
 */
public class ParseException extends Exception {
    public ParseException() {
        super();
    }

    public ParseException(String message){
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseException(Throwable cause) {
        super(cause);
    }
}
