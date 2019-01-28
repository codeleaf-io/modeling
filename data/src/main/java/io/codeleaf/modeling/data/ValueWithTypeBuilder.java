package io.codeleaf.modeling.data;

import java.util.Objects;
import java.util.function.Function;

public final class ValueWithTypeBuilder<T> implements ScalarWithTypeBuilder<T> {

    private final ValueType valueType;
    private final Function<ValueWithType<?>, T> valueWithTypeFunction;

    ValueWithTypeBuilder(ValueType valueType, Function<ValueWithType<?>, T> valueWithTypeFunction) {
        this.valueType = valueType;
        this.valueWithTypeFunction = valueWithTypeFunction;
    }

    @Override
    public T value(ValueWithType<?> valueWithType) {
        Objects.requireNonNull(valueWithType);
        if (!valueWithType.getType().equals(valueType)) {
            throw new IllegalStateException("Invalid type!");
        }
        return valueWithTypeFunction.apply(valueWithType);
    }

    public T enumeration(String value) {
        Objects.requireNonNull(value);
        if (!(valueType instanceof EnumType)) {
            throw new IllegalStateException("We are not an EnumType!");
        }
        return value(EnumWithType.create(value, (EnumType) valueType));
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
