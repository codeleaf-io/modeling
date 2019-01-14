package io.codeleaf.modeling.data;

public final class TimestampType implements ValueType {

    @Override
    public Class<?> getValueClass() {
        return Long.class;
    }

    @Override
    public String toString() {
        return "TimestampType";
    }

    @Override
    public int hashCode() {
        return 37;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TimestampType;
    }

}
