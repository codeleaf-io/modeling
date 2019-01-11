package io.codeleaf.modeling.data;

public final class TimestampType implements ValueType {

    @Override
    public Class<?> getValueClass() {
        return Long.class;
    }

}
