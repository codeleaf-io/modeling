package io.codeleaf.modeling.selection;

import java.util.List;

public final class AndSelection implements CombinationSelection {

    private final List<Selection> selections;

    private AndSelection(List<Selection> selections) {
        this.selections = selections;
    }

    @Override
    public List<Selection> getSelections() {
        return selections;
    }

    public static final class Builder extends AbstractBuilder<AndSelection> {

        @Override
        protected AndSelection build(List<Selection> selections) {
            return new AndSelection(selections);
        }

    }
}
