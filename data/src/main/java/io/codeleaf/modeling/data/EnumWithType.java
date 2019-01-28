package io.codeleaf.modeling.data;

import java.util.Objects;

public final class EnumWithType extends ValueWithType<String> {

    public static EnumWithType create(String value, EnumType enumType) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(enumType);
        if (!enumType.getValues().contains(value)) {
            throw new IllegalArgumentException("No such value present in enum: " + value);
        }
        return new EnumWithType(value, enumType);
    }

    private EnumWithType(String value, EnumType enumType) {
        super(value, enumType);
    }

}
