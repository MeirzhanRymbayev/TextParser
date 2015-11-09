package com.epam.mrymbayev.entity;

import java.util.ArrayList;

/**
 * Created by Meir on 10.11.2015.
 */
public class UnknownToken extends AbstractComposite<Symbol> implements SentenceToken {
    public UnknownToken(){
        components = new ArrayList<>();
    }
}
