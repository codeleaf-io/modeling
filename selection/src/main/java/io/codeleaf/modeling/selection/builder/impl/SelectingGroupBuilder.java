package io.codeleaf.modeling.selection.builder.impl;

import io.codeleaf.modeling.selection.Selectable;

public final class SelectingGroupBuilder<F, V, T> extends SelectingCombinationBuilder<F, V, T, GroupBuilder<F, V, T>> implements GroupBuilder<F, V, T> {

    SelectingGroupBuilder(T returnValue, Selectable selectable) {
        super(returnValue, selectable);
    }

    @Override
    public T endGroup() {
        return end();
    }

}
