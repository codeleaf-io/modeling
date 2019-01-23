package io.codeleaf.modeling.data;

import java.util.Objects;
import java.util.function.Function;

public final class ValueAndTypeBuilder<T> implements ScalarWithTypeBuilder<T> {

    private final Function<ValueWithType<?>, T> valueWithTypeFunction;

    ValueAndTypeBuilder(Function<ValueWithType<?>, T> valueWithTypeFunction) {
        this.valueWithTypeFunction = valueWithTypeFunction;
    }

    @Override
    public T value(ValueWithType<?> valueWithType) {
        Objects.requireNonNull(valueWithType);
        return valueWithTypeFunction.apply(valueWithType);
    }

    public ListAndTypeBuilder<T> beginList() {
        return new ListAndTypeBuilder<>(valueWithTypeFunction);
    }

    public RecordAndTypeBuilder<T> beginRecord() {
        return new RecordAndTypeBuilder<>(valueWithTypeFunction);
    }

    public static ValueAndTypeBuilder<ValueWithType<?>> create() {
        return new ValueAndTypeBuilder<>(valueWithType -> valueWithType);
    }
}
