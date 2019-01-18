package io.codeleaf.modeling.data;

import java.util.Objects;

public class ValueWithType<T> {

    private final T value;
    private final ValueType type;

    protected ValueWithType(T value, ValueType type) {
        this.value = value;
        this.type = type;
    }

    public T getValue() {
        return value;
    }

    public ValueType getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", getClass().getSimpleName(), getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass().getName(), value, type);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ValueWithType)) {
            return false;
        }
        ValueWithType<?> other = (ValueWithType<?>) obj;
        return Objects.equals(other.type, type) && Objects.equals(other.value, value);
    }

}
