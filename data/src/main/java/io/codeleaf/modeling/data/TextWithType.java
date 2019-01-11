package io.codeleaf.modeling.data;

import java.util.Objects;

public final class TextWithType extends ValueWithType<String> {

    public static final TextType TYPE = new TextType();

    public static TextWithType create(String value) {
        Objects.requireNonNull(value);
        return new TextWithType(value);
    }

    private TextWithType(String value) {
        super(value, TYPE);
    }

}
