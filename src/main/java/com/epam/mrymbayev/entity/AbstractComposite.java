package com.epam.mrymbayev.entity;

import com.epam.mrymbayev.entity.Component;
import com.epam.mrymbayev.entity.Composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Meir on 08.11.2015.
 */
public class AbstractComposite<E extends Component> implements Composite<E> {

    List<Component> components;

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components){
        this.components = components;
    }

    public boolean add(E component){
        components.add(component);
        return true;
    }

    public boolean remove(E component) {
        components.remove(component);
        return false;
    }
}
