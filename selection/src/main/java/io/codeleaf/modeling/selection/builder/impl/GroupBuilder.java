package io.codeleaf.modeling.selection.builder.impl;

public interface GroupBuilder<F, V, T> extends CombinationBuilder<F, V, GroupBuilder<F, V, T>> {

    T endGroup();

}
