package io.codeleaf.modeling.selection;

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
        OrSelection.Builder orBuilder = new OrSelection.Builder();
        int i = 0;
        Selection currentSelection = selections.get(i);
        do {
            if (Operator.OR == operators.get(i)) {
                orBuilder.withSelection(currentSelection);
                i++;
                currentSelection = selections.get(i);
            } else {
                AndSelection.Builder andBuilder = new AndSelection.Builder();
                do {
                    andBuilder.withSelection(currentSelection);
                    i++;
                    currentSelection = selections.get(i);
                } while (i < operators.size() && Operator.AND == operators.get(i));
                currentSelection = andBuilder.withSelection(currentSelection).build();
            }
        } while (i < operators.size());
        OrSelection orSelection = orBuilder.withSelection(currentSelection).build();
        return orSelection.getSelections().size() == 1
                ? orSelection.getSelections().get(0)
                : orSelection;
    }

    public static <F, V> SelectionBuilder<F, V, CombinationSelectionBuilder<F, V, Selection>> beginGroup(Class<F> fieldType, Class<V> valueType) {
        return beginGroup();
    }

    public static <F, V> SelectionBuilder<F, V, CombinationSelectionBuilder<F, V, Selection>> beginGroup() {
        return new SelectionBuilder<>(selection -> new CombinationSelectionBuilder<>(selection, s -> s));
    }
}