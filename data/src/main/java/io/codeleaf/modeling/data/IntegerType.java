package io.codeleaf.modeling.data;

public final class IntegerType implements ValueType {

    @Override
    public Class<?> getValueClass() {
        return Long.class;
    }

}
