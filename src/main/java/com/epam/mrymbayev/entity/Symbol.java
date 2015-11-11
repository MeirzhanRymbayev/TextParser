package com.epam.mrymbayev.entity;

/**
 * Symbol class
 * @author Rymbayev Meirzhan
 * @version 1.0
 * @see Leaf
 * Created by Meir on 08.11.2015.
 */
public class Symbol implements Leaf, SentenceToken {

    private char symbol;

    public Symbol(String c) {
        this.symbol = c.charAt(0);
    }

    public String toSourceString() {
        return String.valueOf(symbol);
    }


}
