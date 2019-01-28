package io.codeleaf.modeling.data;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

public final class EnumTypeBuilder<T> {

    private final Set<String> values = new LinkedHashSet<>();

    private final Function<? super EnumType, T> typeFunction;

    EnumTypeBuilder(Function<? super EnumType, T> typeFunction) {
        this.typeFunction = typeFunction;
    }

    public EnumTypeBuilder<T> withValue(String value) {
        Objects.requireNonNull(value);
        if (values.contains(value)) {
            throw new IllegalArgumentException("Duplicate value: " + value);
        }
        values.add(value);
        return this;
    }

    public T endEnum() {
        return typeFunction.apply(EnumType.create(values));
    }

    public static EnumTypeBuilder<EnumType> create() {
        return new EnumTypeBuilder<>(enumType -> enumType);
    }
}
