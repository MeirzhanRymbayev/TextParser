package com.epam.mrymbayev.entity;

import java.util.List;

/**
 * Element of Pattern Composite
 * @author Rymbayev Meirzhan
 * @version 1.0
 * @see Composite
 * @see Leaf
 */
public interface Component {
    String toSourceString();

    <T extends Component> List<T> getClazzComponents(Class<T> clazz, List list);

}
