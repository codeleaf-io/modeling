package io.codeleaf.modeling.selection;

import java.util.function.Function;

public final class SelectionBuilder<F, V, T> {

    private final Function<Selection, T> selectFunction;

    SelectionBuilder(Function<Selection, T> selectFunction) {
        this.selectFunction = selectFunction;
    }

    public SelectionBuilder<F, V, CombinationSelectionBuilder<F, V, T>> beginGroup() {
        return new SelectionBuilder<>(selection -> new CombinationSelectionBuilder<>(selection, selectFunction));
    }

    public SelectionBuilder<F, V, T> field(F fieldName) {
        return new SelectionBuilder<>(selection -> selectFunction.apply(FieldSelection.create(fieldName, selection)));
    }

    public SelectionBuilder<F, V, T> not() {
        return new SelectionBuilder<>(selection -> selectFunction.apply(NotSelection.create(selection)));
    }

    public T smallerThan(V value) {
        return selectFunction.apply(new SmallerThanSelection<>(value));
    }

    public T equalTo(V value) {
        return selectFunction.apply(new EqualToSelection<>(value));
    }

    public T greaterThan(V value) {
        return selectFunction.apply(new GreaterThanSelection<>(value));
    }

    public T inRange(V startIncl, V endExcl) {
        return selectFunction.apply(new InRangeSelection<>(startIncl, endExcl));
    }

    @SafeVarargs
    public final T in(V... value) {
        return selectFunction.apply(InSelection.create(value));
    }

    public static <F, V> SelectionBuilder<F, V, Selection> create(Class<F> fieldType, Class<V> valueType) {
        return create();
    }

    public static <F, V> SelectionBuilder<F, V, Selection> create() {
        return new SelectionBuilder<>(selection -> selection);
    }
}
