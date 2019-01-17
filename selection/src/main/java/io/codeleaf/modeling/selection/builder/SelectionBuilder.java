package io.codeleaf.modeling.selection.builder;

public interface SelectionBuilder<F, T> extends CombinationBuilder<F, SelectionBuilder<F, T>> {

    T endSelection();

}
