package io.codeleaf.modeling.data;

public final class IntegerType implements ValueType {

    @Override
    public Class<?> getValueClass() {
        return Long.class;
    }

    @Override
    public String toString() {
        return "IntegerType";
    }

    @Override
    public int hashCode() {
        return 179;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntegerType;
    }

}
