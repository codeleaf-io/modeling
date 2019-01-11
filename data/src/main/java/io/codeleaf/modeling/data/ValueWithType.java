package io.codeleaf.modeling.data;

public class ValueWithType<T> {

    private final T value;
    private final ValueType type;

    public ValueWithType(T value, ValueType type) {
        this.value = value;
        this.type = type;
    }

    public T getValue() {
        return value;
    }

    public ValueType getType() {
        return type;
    }

}
