package io.codeleaf.modeling.selection.builder;

import io.codeleaf.modeling.selection.*;

public final class TermBuilder<F, T> {

    private final T returnValue;
    private final Selectable selectable;

    TermBuilder(T returnValue, Selectable selectable) {
        this.returnValue = returnValue;
        this.selectable = selectable;
    }

    public TermBuilder<F, GroupBuilder<F, T>> beginGroup() {
        GroupBuilderImpl<F, T> groupBuilder = new GroupBuilderImpl<>(returnValue, selectable);
        return new TermBuilder<>(groupBuilder, groupBuilder);
    }

    public TermBuilder<F, T> field(F fieldName) {
        return new TermBuilder<>(returnValue, (selection) -> selectable.select(FieldSelection.create(fieldName, selection)));
    }

    public TermBuilder<F, T> not() {
        return new TermBuilder<>(returnValue, (selection) -> selectable.select(NotSelection.create(selection)));
    }

    public <O> T smallerThan(O value) {
        selectable.select(new SmallerThanSelection<>(value));
        return returnValue;
    }

    public <O> T equalTo(O value) {
        selectable.select(new EqualToSelection<>(value));
        return returnValue;
    }

    public <O> T greaterThan(O value) {
        selectable.select(new GreaterThanSelection<>(value));
        return returnValue;
    }

    public <O> T inRange(O startIncl, O endExcl) {
        selectable.select(new InRangeSelection<>(startIncl, endExcl));
        return returnValue;
    }

    public <O> T in(O... value) {
        selectable.select(InSelection.create(value));
        return returnValue;
    }

}
