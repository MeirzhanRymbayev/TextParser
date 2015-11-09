package com.epam.mrymbayev.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Meir on 08.11.2015.
 */
public class PMark extends AbstractComposite<Symbol> implements SentenceToken {
    public PMark(){
        components = new ArrayList<>();
    }


}
