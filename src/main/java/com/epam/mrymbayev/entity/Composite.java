package com.epam.mrymbayev.entity;

/**
 * Composite interface
 * @param <E> Generic - <E extends Component> element
 * @author Rymbayev Meirzhan
 * @version 1.0
 * @see Component
 * @see Leaf
 */
public interface Composite<E extends Component> extends Component {
    boolean add(E component);
    boolean remove(E component);
}
