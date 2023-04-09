package io.codeleaf.modeling.data;

import java.util.Objects;

public interface ScalarWithTypeBuilder<T> {

    default T binary(byte[] value) {
        return value(new BinaryWithType(value));
    }

    default T bool(boolean value) {
        return value(new BooleanWithType(value));
    }

    default T integer(long value) {
        return value(new IntegerWithType(value));
    }

    default T identifier(String dataType, String value) {
        Objects.requireNonNull(dataType);
        Objects.requireNonNull(value);
        return value(IdentifierWithType.create(value, dataType));
    }

    default T floatingPoint(double value) {
        return value(new FloatWithType(value));
    }

    default T text(String value) {
        Objects.requireNonNull(value);
        return value(TextWithType.create(value));
    }

    default T timestamp(long value) {
        return value(new TimestampWithType(value));
    }

    T value(ValueWithType<?> valueWithType);
}
