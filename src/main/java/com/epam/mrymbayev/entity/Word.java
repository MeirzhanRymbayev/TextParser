package com.epam.mrymbayev.entity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Word class
 * @author Rymbayev Meirzhan
 * @version 1.0
 * @see AbstractComposite
 * Created by Meir on 08.11.2015.
 */
public class Word extends AbstractComposite<Symbol> implements SentenceToken {
    public Word(){
        components = new ArrayList<>();
    }

    public boolean isWord(){return true;}

}
