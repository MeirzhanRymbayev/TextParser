package com.epam.mrymbayev.entity;

import java.util.ArrayList;

/**
 * PMark class
 * @author Rymbayev Meirzhan
 * @version 1.0
 * @see AbstractComposite
 * Created by Meir on 08.11.2015.
 */
public class PMark extends AbstractComposite<Symbol> implements SentenceToken {
    public PMark() {
        components = new ArrayList<>();
    }


}
