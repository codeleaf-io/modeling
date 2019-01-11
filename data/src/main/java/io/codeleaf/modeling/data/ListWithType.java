package io.codeleaf.modeling.data;

import java.util.List;
import java.util.Objects;

public final class ListWithType extends ValueWithType<List<? extends ValueWithType<?>>> {

    public static ListWithType create(List<? extends ValueWithType<?>> value, ValueType itemValueType) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(itemValueType);
        return new ListWithType(value, ListType.create(itemValueType));
    }

    public static ListWithType create(List<? extends ValueWithType<?>> value, ListType<?> listType) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(listType);
        return new ListWithType(value, listType);
    }

    private ListWithType(List<? extends ValueWithType<?>> value, ListType<?> listType) {
        super(value, listType);
    }

    @Override
    public ListType<?> getType() {
        return (ListType<?>) super.getType();
    }

}
