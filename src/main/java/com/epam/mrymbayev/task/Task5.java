package com.epam.mrymbayev.task;

import com.epam.mrymbayev.entity.Text;

/**
 * Created by Meir on 11.11.2015.
 */
public class Task5 implements Task {
    @Override
    public Text execute(Text parsedText) {
        parsedText.getComponents();
        return null;
    }
}
