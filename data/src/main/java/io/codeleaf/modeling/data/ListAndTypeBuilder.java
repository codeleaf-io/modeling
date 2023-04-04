package io.codeleaf.modeling.data;

import java.util.Objects;
import java.util.function.Function;

public final class ListAndTypeBuilder<T> implements ScalarWithTypeBuilder<ListAndTypeBuilder<T>> {

    private final Function<? super ListWithType, T> valueWithTypeFunction;

    private ListType<?> listType;
    private ListWithType.Builder builder;

    ListAndTypeBuilder(Function<? super ListWithType, T> valueWithTypeFunction) {
        this.valueWithTypeFunction = valueWithTypeFunction;
    }

    public static ListAndTypeBuilder<ListWithType> create() {
        return new ListAndTypeBuilder<>(valueWithType -> valueWithType);
    }

    @Override
    public ListAndTypeBuilder<T> value(ValueWithType<?> valueWithType) {
        Objects.requireNonNull(valueWithType);
        if (builder == null) {
            listType = ListType.create(valueWithType.getType());
            builder = new ListWithType.Builder(listType);
        }
        if (!valueWithType.getType().equals(listType.getItemValueType())) {
            throw new IllegalArgumentException("Invalid type!");
        }
        builder.withItem(valueWithType);
        return this;
    }

    public ListAndTypeBuilder<ListAndTypeBuilder<T>> beginList() {
        return new ListAndTypeBuilder<>(valueWithType -> {
            builder.withItem(valueWithType);
            return this;
        });
    }

    public RecordAndTypeBuilder<ListAndTypeBuilder<T>> beginRecord() {
        return new RecordAndTypeBuilder<>(valueWithType -> {
            builder.withItem(valueWithType);
            return this;
        });
    }

    public T endList() {
        return valueWithTypeFunction.apply(builder.build());
    }
}
