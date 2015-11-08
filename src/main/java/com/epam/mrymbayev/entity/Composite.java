package com.epam.mrymbayev.entity;


public interface Composite<E extends Component> extends Component {
    boolean add(E component);
    boolean remove(E component);
}
