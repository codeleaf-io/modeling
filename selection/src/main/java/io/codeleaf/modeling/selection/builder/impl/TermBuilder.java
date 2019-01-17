package io.codeleaf.modeling.selection.builder.impl;

import io.codeleaf.modeling.selection.*;

public final class TermBuilder<F, V, T> {

    private final T returnValue;
    private final Selectable selectable;

    public TermBuilder(T returnValue, Selectable selectable) {
        this.returnValue = returnValue;
        this.selectable = selectable;
    }

    public TermBuilder<F, V, GroupBuilder<F, V, T>> beginGroup() {
        SelectingGroupBuilder<F, V, T> groupBuilder = new SelectingGroupBuilder<>(returnValue, selectable);
        return new TermBuilder<>(groupBuilder, groupBuilder);
    }

    public TermBuilder<F, V, T> field(F fieldName) {
        return new TermBuilder<>(returnValue, (selection) -> selectable.select(FieldSelection.create(fieldName, selection)));
    }

    public TermBuilder<F, V, T> not() {
        return new TermBuilder<>(returnValue, (selection) -> selectable.select(NotSelection.create(selection)));
    }

    public T smallerThan(V value) {
        selectable.select(new SmallerThanSelection<>(value));
        return returnValue;
    }

    public T equalTo(V value) {
        selectable.select(new EqualToSelection<>(value));
        return returnValue;
    }

    public T greaterThan(V value) {
        selectable.select(new GreaterThanSelection<>(value));
        return returnValue;
    }

    public T inRange(V startIncl, V endExcl) {
        selectable.select(new InRangeSelection<>(startIncl, endExcl));
        return returnValue;
    }

    @SafeVarargs
    public final T in(V... value) {
        selectable.select(InSelection.create(value));
        return returnValue;
    }

}
