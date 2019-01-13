package io.codeleaf.modeling.data;

public final class BooleanType implements ValueType {

    @Override
    public Class<?> getValueClass() {
        return Boolean.class;
    }

    @Override
    public String toString() {
        return "BooleanType";
    }

    @Override
    public int hashCode() {
        return 97;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BooleanType;
    }

}
