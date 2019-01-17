package io.codeleaf.modeling.selection;

public abstract class ComparisonSelection<T> implements Selection {

    private final T value;

    public ComparisonSelection(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

}
