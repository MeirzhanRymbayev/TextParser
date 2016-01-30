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
        if (this == null) return 0;
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

        /*if(this.components.size() != anotherWord.components.size()) return false;
        boolean result = true;
        for (int i = 0; i < components.size(); i++) {
            Symbol symbol = (Symbol) this.components.get(i);
            Symbol anotherWordSymbol = (Symbol) anotherWord.components.get(i);

            if (symbol.equals(anotherWordSymbol)) result = false;
        }
        return result;*/
    }

    @Override
    public String toString() {
        return this.toSourceString();
    }
}
