package io.codeleaf.modeling.selection;

import java.util.List;

public final class OrSelection implements CombinationSelection {

    private final List<Selection> selections;

    private OrSelection(List<Selection> selections) {
        this.selections = selections;
    }

    @Override
    public List<Selection> getSelections() {
        return selections;
    }

    public static final class Builder extends AbstractBuilder<OrSelection> {

        @Override
        protected OrSelection build(List<Selection> selections) {
            return new OrSelection(selections);
        }

    }
}
