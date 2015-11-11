package com.epam.mrymbayev.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Text class
 * @author Rymbayev Meirzhan
 * @version 1.0
 * @see AbstractComposite
 * @see Paragraph
 */
public class Text extends AbstractComposite<Paragraph> {

    public Text(){
        components = new ArrayList<>();
    }

    public Text(List<Component> components){
        this.components = components;
    }

    /**
     * Method remove empty lines from text
     */
    public void removeEmptyLines() {
        Iterator<Component> iter = components.iterator();
        while(iter.hasNext()){
            Component component = iter.next();
            String s = component.toSourceString();
            if(s.isEmpty()){
                iter.remove();
            }
        }
    }

    /**
     * Method return list of paragraphs of text.
     * @return return list of paragraphs of text.
     */
    public List<Paragraph> getParagraphs(){
        List<Paragraph> paragraphs = new ArrayList<>();

        List<Component> paragraphList = this.getComponents();
        for (Component paragraph : paragraphList) {
            paragraphs.add((Paragraph) paragraph);
        }
        return paragraphs;
    }

    /**
     * Method return list of sentences of text.
     * @return return list of sentences of text.
     */
    public List<Sentence> getSentences(){
        List<Sentence> sentencesList = new ArrayList<>();

        List<Paragraph> paragraphList = this.getParagraphs();
        for (Paragraph paragraph: paragraphList) {
            List<Component> componentsList = paragraph.getComponents();
            for (Component sentence: componentsList){
                sentencesList.add((Sentence) sentence);
            }
        }
        return sentencesList;
    }

}
