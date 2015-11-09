package com.epam.mrymbayev.parser.exception;

/**
 * Created by Meir on 10.11.2015.
 */
public class PropertyFilePathException extends Exception {
    public PropertyFilePathException(){
        super();
    }
    public PropertyFilePathException(String message){
        super(message);
    }
    public PropertyFilePathException(String message, Throwable cause){
        super(message, cause);
    }
    public PropertyFilePathException(Throwable cause){
        super(cause);
    }
}
