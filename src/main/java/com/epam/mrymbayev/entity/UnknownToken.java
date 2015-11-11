package com.epam.mrymbayev.entity;

import java.util.ArrayList;

/**
 * UnknownToken class
 * @author Rymbayev Meirzhan
 * @version 1.0
 * @see AbstractComposite
 * @see SentenceToken
 * Created by Meir on 10.11.2015.
 */
public class UnknownToken extends AbstractComposite<Symbol> implements SentenceToken {
    public UnknownToken(){
        components = new ArrayList<>();
    }
}
