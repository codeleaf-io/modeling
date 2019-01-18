package io.codeleaf.modeling.selection;

import java.util.Objects;

public final class NotSelection implements Selection {

    private final Selection selection;

    private NotSelection(Selection selection) {
        this.selection = selection;
    }

    public Selection getSelection() {
        return selection;
    }

    public static NotSelection create(Selection selection) {
        Objects.requireNonNull(selection);
        return new NotSelection(selection);
    }
}
