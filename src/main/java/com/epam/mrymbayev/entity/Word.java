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

    public boolean isWord() {
        return true;
    }

    @Override
    public int hashCode() {
        String word = this.toSourceString();
        return word.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        Word anotherWord = (Word) obj;
        String word = this.toSourceString();
        String anotherWordString = anotherWord.toSourceString();
        return word.equals(anotherWordString);
    }

    @Override
    public String toString() {
        return this.toSourceString();
    }
}
