package com.epam.mrymbayev.entity;

import java.util.ArrayList;

/**
 * Created by Meir on 09.11.2015.
 */
public class Number extends AbstractComposite<Symbol> implements SentenceToken {
    public Number() {
        components = new ArrayList<>();
    }
}
