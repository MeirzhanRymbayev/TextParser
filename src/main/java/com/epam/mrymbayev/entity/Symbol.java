package com.epam.mrymbayev.entity;

import java.util.List;

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

    @Override
    public int hashCode() {
        if(this.symbol == '\u0000') return 0;
        int hash = (int) this.symbol;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        Symbol anotherSymbol = (Symbol) obj;
        return this.symbol == anotherSymbol.symbol;
    }

    @Override//TODO realize without Liskov principle
    public List<Character> getComponentsByClass(Class clazz, List list) {
        return null;
    }
}
