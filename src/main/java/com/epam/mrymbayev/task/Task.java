package com.epam.mrymbayev.task;

import com.epam.mrymbayev.entity.Text;

/**
 * Created by Meir on 11.11.2015.
 */
public interface Task {
    public Text execute(Text parsedText);
}
