package io.codeleaf.modeling.selection.builder;

import io.codeleaf.modeling.selection.Selectable;
import io.codeleaf.modeling.selection.Selection;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractCombinationBuilder<F, T, C extends CombinationBuilder<F, C>> implements CombinationBuilder<F, C>, Selectable {

    private enum Operator {
        AND,
        OR
    }

    private final T returnValue;
    private final Selectable selectable;
    private final List<Selection> selections = new LinkedList<>();
    private final List<Operator> operators = new LinkedList<>();

    AbstractCombinationBuilder(T returnValue, Selectable selectable) {
        this.returnValue = returnValue;
        this.selectable = selectable;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final TermBuilder<F, C> and() {
        operators.add(Operator.AND);
        return new TermBuilder<>((C) this, this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final TermBuilder<F, C> or() {
        operators.add(Operator.OR);
        return new TermBuilder<>((C) this, this);
    }

    @Override
    public void select(Selection selection) {
        selections.add(selection);
    }

    protected final T end() {
        selectable.select(buildSelection());
        return returnValue;
    }

    private Selection buildSelection() {
        if (selections.isEmpty()) {
            throw new IllegalStateException("We must have at least 1 selection!");
        }
        if (selections.size() != operators.size() + 1) {
            throw new IllegalStateException("We have invalid amount of selections with operators!");
        }
        Selection selection;
        if (selections.size() == 1) {
            selection = selections.get(0);
        } else {
            throw new NotImplementedException();
        }
        return selection;
    }

}
