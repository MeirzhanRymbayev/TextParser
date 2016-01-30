package com.epam.mrymbayev.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Sentence class
 * @author Rymbayev Meirzhan
 * @version 1.0
 * @see AbstractComposite
 * Created by Meir on 08.11.2015.
 */
public class Sentence extends AbstractComposite<SentenceToken> {

    public Sentence() {
        components = new ArrayList<>();
    }

    public boolean isSentence(){ return true;}

    /**
     * Method find last word of sentence
     * @return return last word of sentence
     */
    public Word getLastWord(){
        int tokenIndex = components.size() - 1;
        while(true){
            if(components.get(tokenIndex).getClass() == Word.class) break;
            tokenIndex--;
        }
        return (Word) components.get(tokenIndex);
    }

    /**
     * Method find first word of sentence
     * @return return first word of sentence
     */
    public Word getFirstWord(){
        int tokenIndex = 0;
        while(true){
            if(components.get(tokenIndex).getClass() == Word.class) break;
            tokenIndex++;
        }
        return (Word) components.get(tokenIndex);
    }

    public Sentence reverseWords(Word firstWord, Word secondWord){
        List<Component> resultSentence = new LinkedList<>(this.components);
            int indexOfFisrtWord = components.indexOf(firstWord);
            int indexOfLastWord = components.indexOf(secondWord);
            resultSentence.set(indexOfFisrtWord, secondWord);
            resultSentence.set(indexOfLastWord, firstWord);
        this.components = resultSentence;
        return this;
    }

    public List<Word> getWords(){
        List<Word> words = new ArrayList<>();
        for (Component component :
                components) {
            if(component.getClass() == Word.class){
                words.add((Word) component);
            }

        }
        return words;
    }



}
