package io.codeleaf.modeling.data;

import java.util.Objects;

public final class IdentifierWithType extends ValueWithType<String> {

    public static IdentifierWithType create(String value, String dataType) {
        return create(value, IdentifierType.create(dataType));
    }

    public static IdentifierWithType create(String value, IdentifierType type) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(type);
        return new IdentifierWithType(value, type);
    }

    private IdentifierWithType(String value, IdentifierType type) {
        super(value, type);
    }

}
