package com.epam.mrymbayev.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Meir on 08.11.2015.
 */
public class Sentence extends AbstractComposite<SentenceToken> {

    public Sentence(){
        components = new ArrayList<>();
    }

}
