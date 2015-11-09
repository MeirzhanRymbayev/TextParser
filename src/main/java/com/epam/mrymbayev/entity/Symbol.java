package com.epam.mrymbayev.entity;

/**
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
