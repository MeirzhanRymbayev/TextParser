package com.epam.mrymbayev.task;

import com.epam.mrymbayev.entity.Paragraph;
import com.epam.mrymbayev.entity.Sentence;
import com.epam.mrymbayev.entity.Text;
import com.epam.mrymbayev.entity.Word;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Task5 class
 * It is class were task N5 will be executed
 * @author Rymbayev Meirzhan
 * @version 1.0
 * @see Task
 */
public class Task5 implements Task {

    Logger log = Logger.getLogger(Task5.class);

    /**
     * Method execute task. Method reverse first and last words in every sentence of text.
     * @param parsedText
     * @return
     */
    @Override
    public Text execute(Text parsedText) {
        log.info("Execution of task run...");
        Text resultText = new Text();

        parsedText.removeEmptyLines();
        log.info("Empty lines were removed from parsed text.");
        List<Paragraph> paragraphs = parsedText.getParagraphs();
        for (Paragraph paragraph : paragraphs) {
            List<Sentence> resultParagraph = new ArrayList<>();
            List<Sentence> oldParagraph = paragraph.getSentences();
            for (Sentence sentence : oldParagraph) {
                Word firstWord = sentence.getFirstWord();
                Word lastWord = sentence.getLastWord();
                Sentence resultSentence = sentence.reverseWords(firstWord, lastWord);
                resultParagraph.add(resultSentence);
            }
            resultText.add(new Paragraph(resultParagraph));
        }
                log.info("Words in sentences place's were replaced each other.");
        log.info("Result text ready.");
        return resultText;
    }
}
