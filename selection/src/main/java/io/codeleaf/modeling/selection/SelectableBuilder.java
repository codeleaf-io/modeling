package io.codeleaf.modeling.selection;

public interface SelectableBuilder<F, V, T extends SelectableBuilder<F, V, T>> extends Selectable {

    @SuppressWarnings("unchecked")
    default T withSelection(Selection selection) {
        this.select(selection);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    default SelectionBuilder<F, V, T> withSelection() {
        return new SelectionBuilder<>(selection -> {
            this.select(selection);
            return (T) this;
        });
    }
}
