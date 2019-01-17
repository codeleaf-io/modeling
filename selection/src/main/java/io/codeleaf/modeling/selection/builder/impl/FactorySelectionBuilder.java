package io.codeleaf.modeling.selection.builder.impl;

import io.codeleaf.modeling.selection.Selection;

public final class FactorySelectionBuilder<F, V> extends AbstractCombinationBuilder<F, V, SelectionBuilder<F, V, Selection>> implements SelectionBuilder<F, V, Selection> {

    public FactorySelectionBuilder() {
    }

    @Override
    public Selection endSelection() {
        return getSelection();
    }

}
