package io.codeleaf.modeling.selection.builder;

import io.codeleaf.modeling.selection.Selectable;

public final class SelectionBuilderImpl<F, T> extends AbstractCombinationBuilder<F, T, SelectionBuilder<F, T>> implements SelectionBuilder<F, T> {

    SelectionBuilderImpl(T returnValue, Selectable selectable) {
        super(returnValue, selectable);
    }

    @Override
    public T endSelection() {
        return end();
    }

}
