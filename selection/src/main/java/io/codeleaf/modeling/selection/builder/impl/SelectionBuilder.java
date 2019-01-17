package io.codeleaf.modeling.selection.builder.impl;

public interface SelectionBuilder<F, V, T> extends CombinationBuilder<F, V, SelectionBuilder<F, V, T>> {

    T endSelection();

}
