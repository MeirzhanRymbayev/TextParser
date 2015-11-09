package com.epam.mrymbayev.entity;

import java.util.ArrayList;

/**
 * Created by Meir on 08.11.2015.
 */
public class Paragraph extends AbstractComposite<Sentence> {
    public Paragraph(){
        components = new ArrayList<>();
    }
}
