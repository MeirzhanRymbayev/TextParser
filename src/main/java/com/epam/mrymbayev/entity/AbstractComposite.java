package com.epam.mrymbayev.entity;

import java.util.List;

/**
 * Abstract composite class, parent class for Word, PMark, Number, Sentence, Paragraph, Text classes.
 * @param <E> Generic - <E extends Component> element
 */
public class AbstractComposite<E extends Component> implements Composite<E> {

    List<Component> components;

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public boolean add(E component) {
        components.add(component);
        return true;
    }

    public boolean remove(E component) {
        components.remove(component);
        return false;
    }

    public String toSourceString() {
        StringBuilder builder = new StringBuilder();
        for (Component component : components) builder.append(component.toSourceString());
        return builder.toString();
    }

}
