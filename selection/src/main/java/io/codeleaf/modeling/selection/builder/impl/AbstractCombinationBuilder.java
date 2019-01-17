package io.codeleaf.modeling.selection.builder.impl;

import io.codeleaf.modeling.selection.Selectable;
import io.codeleaf.modeling.selection.Selection;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractCombinationBuilder<F, V, C extends CombinationBuilder<F, V, C>> implements CombinationBuilder<F, V, C>, Selectable {

    private enum Operator {
        AND,
        OR
    }

    private final List<Selection> selections = new LinkedList<>();
    private final List<Operator> operators = new LinkedList<>();

    AbstractCombinationBuilder() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public final TermBuilder<F, V, C> and() {
        operators.add(Operator.AND);
        return new TermBuilder<>((C) this, this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final TermBuilder<F, V, C> or() {
        operators.add(Operator.OR);
        return new TermBuilder<>((C) this, this);
    }

    @Override
    public void select(Selection selection) {
        selections.add(selection);
    }

    public Selection getSelection() {
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
        return selection;
    }

    private Selection buildSelection() {
        throw new NotImplementedException();
    }

}
