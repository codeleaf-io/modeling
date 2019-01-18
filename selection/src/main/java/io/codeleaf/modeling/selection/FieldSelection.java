package io.codeleaf.modeling.selection;

import java.util.Objects;

public final class FieldSelection<T> implements Selection {

    private final T field;
    private final Selection selection;

    private FieldSelection(T field, Selection selection) {
        this.field = field;
        this.selection = selection;
    }

    public T getField() {
        return field;
    }

    public Selection getSelection() {
        return selection;
    }

    public static <T> FieldSelection<T> create(T field, Selection selection) {
        Objects.requireNonNull(field);
        Objects.requireNonNull(selection);
        return new FieldSelection<>(field, selection);
    }
}
