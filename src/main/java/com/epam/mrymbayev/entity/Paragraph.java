package com.epam.mrymbayev.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Paragraph class
 * @author Rymbayev Meirzhan
 * @version 1.0
 * @see AbstractComposite
 * Created by Meir on 08.11.2015.
 */
public class Paragraph extends AbstractComposite<Sentence> {
    public Paragraph() {
        components = new ArrayList<>();
    }

    public Paragraph(List<Sentence> sentences){
        components = new ArrayList<Component>(sentences);
    }


    /**
     * Method return list of sentences of paragraph.
     * @return return list of sentences of paragraph.
     */
    public List<Sentence> getSentences(){
        List<Sentence> sentencesList = new ArrayList<>();
            List<Component> componentsList = this.getComponents();
            for (Component sentence: componentsList){
                sentencesList.add((Sentence) sentence);
            }
        return sentencesList;
    }
}
