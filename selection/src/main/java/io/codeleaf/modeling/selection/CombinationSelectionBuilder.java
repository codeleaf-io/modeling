package io.codeleaf.modeling.selection;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public final class CombinationSelectionBuilder<F, V, T> {

    private enum Operator {
        AND,
        OR
    }

    private final List<Selection> selections = new LinkedList<>();
    private final List<Operator> operators = new LinkedList<>();
    private final Function<Selection, T> selectFunction;

    CombinationSelectionBuilder(Selection selection, Function<Selection, T> selectFunction) {
        selections.add(selection);
        this.selectFunction = selectFunction;
    }

    public SelectionBuilder<F, V, CombinationSelectionBuilder<F, V, T>> and() {
        operators.add(Operator.AND);
        return new SelectionBuilder<>(selection -> {
            selections.add(selection);
            return this;
        });
    }

    public SelectionBuilder<F, V, CombinationSelectionBuilder<F, V, T>> or() {
        operators.add(Operator.OR);
        return new SelectionBuilder<>(selection -> {
            selections.add(selection);
            return this;
        });
    }

    public T endGroup() {
        Selection selection;
        if (selections.isEmpty()) {
            selection = null;
        } else if (selections.size() != operators.size() + 1) {
            throw new IllegalStateException("We have invalid amount of selections with operators!");
        } else if (selections.size() == 1) {
            selection = selections.get(0);
        } else {
            selection = buildSelection();
        }
        return selectFunction.apply(selection);
    }

    private Selection buildSelection() {
        throw new NotImplementedException();
    }

    public static <F, V> SelectionBuilder<F, V, CombinationSelectionBuilder<F, V, Selection>> beginGroup(Class<F> fieldType, Class<V> valueType) {
        return beginGroup();
    }

    public static <F, V> SelectionBuilder<F, V, CombinationSelectionBuilder<F, V, Selection>> beginGroup() {
        return new SelectionBuilder<>(selection -> new CombinationSelectionBuilder<>(selection, s -> s));
    }
}