package io.codeleaf.modeling.selection.builder;

public interface GroupBuilder<F, T> extends CombinationBuilder<F, GroupBuilder<F, T>> {

    T endGroup();

}
