package com.epam.mrymbayev.entity;

import java.util.ArrayList;

/**
 * Word class
 *
 * @author Rymbayev Meirzhan
 * @version 1.0
 * @see AbstractComposite
 * Created by Meir on 08.11.2015.
 */
public class Word extends AbstractComposite<Symbol> implements SentenceToken {
    public Word() {
        components = new ArrayList<>();
    }

//    public static void main(String[] args) {
//        int x = 4, c = 8;
//        System.out.println(x | c);
//
////        char c = '\u0000';
////        char d = 0;
////        int s = (int) c;
////        System.out.println(c == d);
////        System.out.println(4 | 3);
////        System.out.println("end");
//    }

    public boolean isWord() {
        return true;
    }

    @Override
    public int hashCode() {
        if (this == null) return 0;
        int hash = 0;
        for (Component symbol :
                components) {
            hash += symbol.hashCode() * 2;
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        Word anotherWord = (Word) obj;
        boolean result = true;
        for (int i = 0; i < components.size(); i++) {
            Symbol symbol = (Symbol) this.components.get(i);
            Symbol anotherWordSymbol = (Symbol) anotherWord.components.get(i);

            if (symbol.equals(anotherWordSymbol)) result = false;
        }
        return result;
    }


}
