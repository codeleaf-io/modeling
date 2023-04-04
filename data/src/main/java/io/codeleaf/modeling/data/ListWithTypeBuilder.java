package io.codeleaf.modeling.data;

import java.util.Objects;
import java.util.function.Function;

public final class ListWithTypeBuilder<T> implements ScalarWithTypeBuilder<ListWithTypeBuilder<T>> {

    private final ListType<?> listType;
    private final Function<? super ListWithType, T> valueWithTypeFunction;
    private final ListWithType.Builder builder;

    ListWithTypeBuilder(ListType<?> listType, Function<? super ListWithType, T> valueWithTypeFunction) {
        this.listType = listType;
        this.valueWithTypeFunction = valueWithTypeFunction;
        builder = new ListWithType.Builder(listType);
    }

    public static ListWithTypeBuilder<ListWithType> create(ListType<?> listType) {
        Objects.requireNonNull(listType);
        return new ListWithTypeBuilder<>(listType, listWithType -> listWithType);
    }

    public ListWithTypeBuilder<T> value(ValueWithType<?> valueWithType) {
        Objects.requireNonNull(valueWithType);
        if (!valueWithType.getType().equals(listType.getItemValueType())) {
            throw new IllegalArgumentException("Invalid type!");
        }
        builder.withItem(valueWithType);
        return this;
    }

    public ListWithTypeBuilder<ListWithTypeBuilder<T>> beginList() {
        if (!(listType.getItemValueType() instanceof ListType)) {
            throw new IllegalStateException("List does not contain lists!");
        }
        return new ListWithTypeBuilder<>((ListType<?>) listType.getItemValueType(), valueWithType -> {
            builder.withItem(valueWithType);
            return this;
        });
    }

    public RecordWithTypeBuilder<ListWithTypeBuilder<T>> beginRecord() {
        if (!(listType.getItemValueType() instanceof RecordType)) {
            throw new IllegalStateException("List does not contain records!");
        }
        return new RecordWithTypeBuilder<>((RecordType) listType.getItemValueType(), valueWithType -> {
            builder.withItem(valueWithType);
            return this;
        });
    }

    public T endList() {
        return valueWithTypeFunction.apply(builder.build());
    }
}
