package com.epam.mrymbayev.task;

import com.epam.mrymbayev.entity.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Meir on 11.11.2015.
 */
public class Task5 implements Task {
    @Override
    public Text execute(Text parsedText) {
        Text resultText = new Text();

        parsedText.removeEmptyLines();
        List<Paragraph> paragraphs = parsedText.getParagraphs();
        for (Paragraph paragraph: paragraphs) {
            List<Sentence> resultParagraph = new ArrayList<>();
                List<Sentence> oldParagraph = paragraph.getSentences();
                    for(Sentence sentence: oldParagraph){
                        Word firstWord = sentence.getFirstWord();
                        Word lastWord = sentence.getLastWord();
                        Sentence resultSentence = sentence.reverseWords(firstWord, lastWord);
                        resultParagraph.add(resultSentence);
                    }
                resultText.add(new Paragraph(resultParagraph));
        }
        return resultText;
    }
}
