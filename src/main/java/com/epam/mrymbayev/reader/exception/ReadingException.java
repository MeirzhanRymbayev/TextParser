package com.epam.mrymbayev.reader.exception;

/**
 * Created by Meir on 10.11.2015.
 */
public class ReadingException extends Exception {
    public ReadingException() {
        super();
    }

    public ReadingException(String message) {
        super(message);
    }

    public ReadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReadingException(Throwable cause) {
        super(cause);
    }
}
