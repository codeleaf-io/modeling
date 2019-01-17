package io.codeleaf.modeling.selection;

public final class EqualToSelection<T> extends ComparisonSelection<T> {

    public EqualToSelection(T value) {
        super(value);
    }

}
