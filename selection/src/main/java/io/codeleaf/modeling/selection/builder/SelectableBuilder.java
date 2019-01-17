package io.codeleaf.modeling.selection.builder;

import io.codeleaf.modeling.selection.Selectable;

public interface SelectableBuilder<F, T extends SelectableBuilder> extends Selectable {

    @SuppressWarnings("unchecked")
    default TermBuilder<F, SelectionBuilder<F, T>> beginSelection() {
        SelectionBuilderImpl<F, T> selectionBuilder = new SelectionBuilderImpl<>((T) this, this);
        return new TermBuilder<>(selectionBuilder, selectionBuilder);
    }

}
