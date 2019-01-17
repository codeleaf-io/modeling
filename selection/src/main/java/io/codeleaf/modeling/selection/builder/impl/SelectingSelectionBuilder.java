package io.codeleaf.modeling.selection.builder.impl;

import io.codeleaf.modeling.selection.Selectable;

public final class SelectingSelectionBuilder<F, V, T> extends SelectingCombinationBuilder<F, V, T, SelectionBuilder<F, V, T>> implements SelectionBuilder<F, V, T> {

    public SelectingSelectionBuilder(T returnValue, Selectable selectable) {
        super(returnValue, selectable);
    }

    @Override
    public T endSelection() {
        return end();
    }

}
