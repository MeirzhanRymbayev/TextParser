package com.epam.mrymbayev.entity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Meir on 08.11.2015.
 */
public class Word extends AbstractComposite<Symbol> implements SentenceToken {
    public Word(){
        components = new ArrayList<>();
    }



}
