package io.codeleaf.modeling.data;

import java.util.Objects;
import java.util.function.Function;

public final class ValueWithTypeBuilder<T> {

    private final ValueType valueType;
    private final Function<ValueWithType<?>, T> valueWithTypeFunction;

    ValueWithTypeBuilder(ValueType valueType, Function<ValueWithType<?>, T> valueWithTypeFunction) {
        this.valueType = valueType;
        this.valueWithTypeFunction = valueWithTypeFunction;
    }

    public T value(ValueWithType<?> valueWithType) {
        Objects.requireNonNull(valueWithType);
        if (!valueWithType.getType().equals(valueType)) {
            throw new IllegalArgumentException("Invalid type!");
        }
        return valueWithTypeFunction.apply(valueWithType);
    }

    public T bool(boolean value) {
        return value(new BooleanWithType(value));
    }

    public T integer(long value) {
        return value(new IntegerWithType(value));
    }

    public T identifier(String dataType, String value) {
        Objects.requireNonNull(dataType);
        Objects.requireNonNull(value);
        return value(IdentifierWithType.create(value, dataType));
    }

    public T text(String value) {
        Objects.requireNonNull(value);
        return value(TextWithType.create(value));
    }

    public T timestamp(long value) {
        return value(new TimestampWithType(value));
    }

    public ListWithTypeBuilder<T> beginList() {
        if (!(valueType instanceof ListType)) {
            throw new IllegalStateException("ValueType is not a List!");
        }
        return new ListWithTypeBuilder<>((ListType<?>) valueType, valueWithTypeFunction);
    }

    public RecordWithTypeBuilder<T> beginRecord() {
        if (!(valueType instanceof RecordType)) {
            throw new IllegalStateException("ValueType is not a Record!");
        }
        return new RecordWithTypeBuilder<>((RecordType) valueType, valueWithTypeFunction);
    }

    public static ValueWithTypeBuilder<ValueWithType<?>> create(ValueType valueType) {
        Objects.requireNonNull(valueType);
        return new ValueWithTypeBuilder<>(valueType, valueWithType -> valueWithType);
    }
}
