package io.codeleaf.modeling.data;

import java.util.Objects;
import java.util.function.Function;

public final class ListWithTypeBuilder<T> {

    private final ListType<?> listType;
    private final Function<ValueWithType<?>, T> valueWithTypeFunction;
    private final ListWithType.Builder builder;

    ListWithTypeBuilder(ListType<?> listType, Function<ValueWithType<?>, T> valueWithTypeFunction) {
        this.listType = listType;
        this.valueWithTypeFunction = valueWithTypeFunction;
        builder = new ListWithType.Builder(listType);
    }

    public ListWithTypeBuilder<T> value(ValueWithType<?> valueWithType) {
        Objects.requireNonNull(valueWithType);
        if (!valueWithType.getType().equals(listType.getItemValueType())) {
            throw new IllegalArgumentException("Invalid type!");
        }
        builder.withItem(valueWithType);
        return this;
    }

    public ListWithTypeBuilder<T> bool(boolean value) {
        return value(new BooleanWithType(value));
    }

    public ListWithTypeBuilder<T> integer(long value) {
        return value(new IntegerWithType(value));
    }

    public ListWithTypeBuilder<T> identifier(String dataType, String value) {
        Objects.requireNonNull(dataType);
        Objects.requireNonNull(value);
        return value(IdentifierWithType.create(value, dataType));
    }

    public ListWithTypeBuilder<T> text(String value) {
        Objects.requireNonNull(value);
        return value(TextWithType.create(value));
    }

    public ListWithTypeBuilder<T> timestamp(long value) {
        return value(new TimestampWithType(value));
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

    public static ListWithTypeBuilder<ListWithType> beginList(ListType<?> listType) {
        Objects.requireNonNull(listType);
        return new ListWithTypeBuilder<>(listType, valueWithType -> (ListWithType) valueWithType);
    }
}
