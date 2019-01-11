package io.codeleaf.modeling.data;

import java.util.List;
import java.util.Objects;

public final class ListType<I extends ValueType> implements ValueType {

    public static <I extends ValueType> ListType<I> create(I itemValueType) {
        Objects.requireNonNull(itemValueType);
        return new ListType<>(itemValueType);
    }

    private final I itemValueType;

    private ListType(I itemValueType) {
        this.itemValueType = itemValueType;
    }

    public I getItemValueType() {
        return itemValueType;
    }

    @Override
    public Class<?> getValueClass() {
        return List.class;
    }

}
