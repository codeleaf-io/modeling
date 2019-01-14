package io.codeleaf.modeling.data;

public final class TextType implements ValueType {

    @Override
    public Class<?> getValueClass() {
        return String.class;
    }

    @Override
    public String toString() {
        return "TextType";
    }

    @Override
    public int hashCode() {
        return 61;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TextType;
    }

}
