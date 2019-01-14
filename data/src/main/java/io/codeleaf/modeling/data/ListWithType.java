package io.codeleaf.modeling.data;

import java.util.*;

public final class ListWithType extends ValueWithType<List<? extends ValueWithType<?>>> {

    public static ListWithType create(ValueType itemValueType, List<? extends ValueWithType<?>> value) {
        Objects.requireNonNull(itemValueType);
        Objects.requireNonNull(value);
        return create(value, ListType.create(itemValueType));
    }

    public static ListWithType create(ValueType itemValueType, ValueWithType<?>... value) {
        Objects.requireNonNull(itemValueType);
        List<ValueWithType<?>> listValue;
        if (value == null) {
            listValue = Collections.emptyList();
        } else {
            for (ValueWithType<?> providedValue : value) {
                if (!itemValueType.equals(providedValue.getType())) {
                    throw new IllegalArgumentException("Invalid type specified as value!");
                }
            }
            listValue = Arrays.asList(value);
        }
        return create(listValue, ListType.create(itemValueType));
    }

    public static ListWithType create(List<? extends ValueWithType<?>> value, ListType<?> listType) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(listType);
        return new ListWithType(Collections.unmodifiableList(new ArrayList<>(value)), listType);
    }

    private ListWithType(List<? extends ValueWithType<?>> value, ListType<?> listType) {
        super(value, listType);
    }

    @Override
    public ListType<?> getType() {
        return (ListType<?>) super.getType();
    }

    @Override
    public int hashCode() {
        int hash = 250193 + Objects.hashCode(getType());
        for (ValueWithType<?> item : getValue()) {
            hash += Objects.hashCode(item);
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListWithType)) {
            return false;
        }
        ListWithType other = (ListWithType) obj;
        if (getValue().size() != other.getValue().size() || !Objects.equals(getType(), other.getType())) {
            return false;
        }
        for (int i = 0; i < getValue().size(); i++) {
            if (!(Objects.equals(getValue().get(i), other.getValue().get(i)))) {
                return false;
            }
        }
        return true;
    }

}
