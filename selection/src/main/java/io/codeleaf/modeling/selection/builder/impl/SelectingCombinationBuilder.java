package io.codeleaf.modeling.selection.builder.impl;

import io.codeleaf.modeling.selection.Selectable;

public class SelectingCombinationBuilder<F, V, T, C extends CombinationBuilder<F, V, C>> extends AbstractCombinationBuilder<F, V, C> {

    private final T returnValue;
    private final Selectable selectable;

    SelectingCombinationBuilder(T returnValue, Selectable selectable) {
        this.returnValue = returnValue;
        this.selectable = selectable;
    }

    protected final T end() {
        selectable.select(getSelection());
        return returnValue;
    }

}
