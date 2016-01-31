package com.epam.mrymbayev.task;

import com.epam.mrymbayev.entity.Text;
import com.epam.mrymbayev.entity.Word;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;

public class SpringInterviewTask implements Task {
    Logger log = Logger.getLogger(SpringInterviewTask.class);

    @Override
    public Text execute(Text parsedText) {
        throw new UnsupportedOperationException();
    }

    public Map<Word, Integer> getWordsOftenCount(Text text) {
        List<Word> words = text.getComponentsByClass(Word.class, new ArrayList());
        List<Word> wordsCopy = new ArrayList<>(words);

        Map<Word, Integer> map = new HashMap<>();
        HashSet<Word> wordsSet = new HashSet<>();

        Iterator<Word> wordsIterator = words.iterator();
        Iterator<Word> wordsCopyIterator;
        Integer quantity;
        while (wordsIterator.hasNext()) {
            quantity = 0;
            Word word = wordsIterator.next();
            wordsCopyIterator = wordsCopy.iterator();
            while (wordsCopyIterator.hasNext()) {
                Word anotherWord = wordsCopyIterator.next();
                if (word.equals(anotherWord)) {
                    wordsCopyIterator.remove();
                    quantity = quantity + 1;
                }
            }
            if (!wordsSet.contains(word)) { // If this word didn't happened before we'll add it into map.
                wordsSet.add(word);
                map.put(word, quantity);
            }

        }
        return map;
    }


    public void writeToFile(String s, String path) {
        try (PrintWriter out = new PrintWriter(path)) {
            out.println(s);
            log.info("Text was wrote to " + path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
