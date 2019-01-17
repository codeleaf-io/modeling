package io.codeleaf.modeling.selection.builder;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.modeling.selection.builder.impl.CombinationBuilder;
import io.codeleaf.modeling.selection.builder.impl.FactorySelectionBuilder;
import io.codeleaf.modeling.selection.builder.impl.SelectionBuilder;
import io.codeleaf.modeling.selection.builder.impl.TermBuilder;

public final class SelectionFactory {

    public static <F, V> TermBuilder<F, V, SelectionBuilder<F, V, Selection>> beginSelection() {
        FactorySelectionBuilder<F, V> selectionBuilder = new FactorySelectionBuilder<>();
        return new TermBuilder<>(selectionBuilder, selectionBuilder);
    }

    public static <F, V> TermBuilder<F, V, SelectionBuilder<F, V, Selection>> beginSelection(Class<F> fieldNameType, Class<V> valueType) {
        return beginSelection();
    }

    private SelectionFactory() {
    }

}
