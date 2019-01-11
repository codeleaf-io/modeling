package io.codeleaf.modeling.data;

public final class BooleanType implements ValueType {

    @Override
    public Class<?> getValueClass() {
        return Boolean.class;
    }

}
