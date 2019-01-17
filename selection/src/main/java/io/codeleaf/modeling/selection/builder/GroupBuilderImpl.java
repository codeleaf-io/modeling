package io.codeleaf.modeling.selection.builder;

import io.codeleaf.modeling.selection.Selectable;

public final class GroupBuilderImpl<F, T> extends AbstractCombinationBuilder<F, T, GroupBuilder<F, T>> implements GroupBuilder<F, T> {

    GroupBuilderImpl(T returnValue, Selectable selectable) {
        super(returnValue, selectable);
    }

    @Override
    public T endGroup() {
        return end();
    }

}
