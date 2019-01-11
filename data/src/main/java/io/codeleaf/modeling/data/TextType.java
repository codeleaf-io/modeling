package io.codeleaf.modeling.data;

public final class TextType implements ValueType {

    @Override
    public Class<?> getValueClass() {
        return String.class;
    }

}
