package io.codeleaf.modeling.data;

import java.util.*;

public final class ListWithType extends ValueWithType<List<? extends ValueWithType<?>>> {

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
        ValueType itemValueType = listType.getItemValueType();
        if (itemValueType == null) {
            throw new IllegalArgumentException("listType contains null ValueType!");
        }
        Map<Integer, ValueWithType<?>> invalidItems = new LinkedHashMap<>();
        for (int i = 0; i < value.size(); i++) {
            ValueWithType<?> item = value.get(i);
            if (item == null || !itemValueType.equals(item.getType())) {
                invalidItems.put(i, item);
            }
        }
        if (!invalidItems.isEmpty()) {
            StringBuilder builder = new StringBuilder("Invalid values in list of type ").append(itemValueType).append(": ");
            for (Map.Entry<Integer, ValueWithType<?>> entry : invalidItems.entrySet()) {
                builder.append(entry.getKey()).append(": ");
                String reason;
                if (entry.getValue() == null) {
                    reason = "null";
                } else {
                    reason = String.format("invalid type [found %s]", entry.getValue().getType());
                }
                builder.append(reason).append(", ");
            }
            builder.setLength(builder.length() - 2);
            throw new IllegalArgumentException(builder.toString());
        }
        return new ListWithType(Collections.unmodifiableList(new ArrayList<>(value)), listType);
    }

    public static final class Builder {

        private final List<ValueWithType<?>> items = new ArrayList<>();
        private final ListType<?> listType;

        public Builder(ListType<?> listType) {
            Objects.requireNonNull(listType);
            this.listType = listType;
        }

        public Builder withItem(ValueWithType<?> item) {
            Objects.requireNonNull(item);
            if (!listType.getItemValueType().equals(item.getType())) {
                throw new IllegalArgumentException("Invalid type specified!");
            }
            items.add(item);
            return this;
        }

        public ListWithType build() {
            return create(items, listType);
        }
    }
}
