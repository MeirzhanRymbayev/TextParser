package com.epam.mrymbayev.entity;

/**
 * Created by Meir on 08.11.2015.
 */
public class PMark extends AbstractComposite<Symbol> implements SentenceToken {


    public boolean add(Symbol component) {
        return false;
    }

    public boolean remove(Symbol component) {
        return false;
    }
}
