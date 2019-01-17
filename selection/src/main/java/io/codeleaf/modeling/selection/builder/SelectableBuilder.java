package io.codeleaf.modeling.selection.builder;

import io.codeleaf.modeling.selection.Selectable;
import io.codeleaf.modeling.selection.builder.impl.SelectingSelectionBuilder;
import io.codeleaf.modeling.selection.builder.impl.SelectionBuilder;
import io.codeleaf.modeling.selection.builder.impl.TermBuilder;

public interface SelectableBuilder<F, V, T extends SelectableBuilder> extends Selectable {

    @SuppressWarnings("unchecked")
    default TermBuilder<F, V, SelectionBuilder<F, V, T>> beginSelection() {
        SelectingSelectionBuilder<F, V, T> selectionBuilder = new SelectingSelectionBuilder<>((T) this, this);
        return new TermBuilder<>(selectionBuilder, selectionBuilder);
    }

}
