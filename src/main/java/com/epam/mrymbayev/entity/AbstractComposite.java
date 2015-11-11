package com.epam.mrymbayev.entity;

import java.util.List;

/**
 * Abstract composite class, parent class for Word, PMark, Number, Sentence, Paragraph, Text classes.
 * @param <E> Generic - <E extends Component> element
 * @author Rymbayev Meirzhan
 * @version 1.0
 * @see Composite
 */
public class AbstractComposite<E extends Component> implements Composite<E> {

    List<Component> components;

    /**
     *
     * @return return List<Component> components
     */
    public List<Component> getComponents() {
        return components;
    }

    /**
     * Method get element from components list by index of element
     * @param index index of element which should be return
     * @return return element of list by index parameter
     */
    public Component getComponentAt(int index){
        return components.get(index);
    }

    /**
     * Method remove element from components list by index of element
     * @param index index of element which should be deleted
     * @return return components without deleted element
     */
    public List<Component> removeComponentAt(int index){
        return (List<Component>) components.remove(index);
    }

    /**
     * Set this.components field of object to parameter which is passed
     * @param components
     */
    public void setComponents(List<Component> components) {
        this.components = components;
    }

    /**
     * Method add E component into this Composite element.
     * @param component will be added
     * @return boolean true, if operation was succesfully and false, if not
     */
    public boolean add(E component) {
        components.add(component);
        return true;
    }

    /**
     * Method remove E component from this Composite element.
     * @param component will be deleted
     * @return boolean true, if operation was succesfully and false, if not
     */
    public boolean remove(E component) {
        components.remove(component);
        return false;
    }

    /**
     * Method return readable String of <? extends AbstractComposite> element.
     * @return Method return readable String of <? extends AbstractComposite> element.
     */
    public String toSourceString() {
        StringBuilder builder = new StringBuilder();
        for (Component component : components) builder.append(component.toSourceString());
        return builder.toString();
    }

}
