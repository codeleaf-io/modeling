package io.codeleaf.modeling.data;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public final class MapWithType extends ValueWithType<Map<String, ? extends ValueWithType<?>>> {

    private MapWithType(Map<String, ? extends ValueWithType<?>> value, MapType<?> mapType) {
        super(value, mapType);
    }

    public static MapWithType create(ValueType entryValueType, Map<String, ? extends ValueWithType<?>> value) {
        Objects.requireNonNull(entryValueType);
        Objects.requireNonNull(value);
        return create(value, MapType.create(entryValueType));
    }

    public static MapWithType create(Map<String, ? extends ValueWithType<?>> value, MapType<?> mapType) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(mapType);
        ValueType entryValueType = mapType.getEntryValueType();
        if (entryValueType == null) {
            throw new IllegalArgumentException("mapType contains null ValueType!");
        }
        Map<String, ValueWithType<?>> invalidItems = new LinkedHashMap<>();
        for (String key : value.keySet()) {
            ValueWithType<?> item = value.get(key);
            if (item == null || !entryValueType.equals(item.getType())) {
                invalidItems.put(key, item);
            }
        }
        if (!invalidItems.isEmpty()) {
            StringBuilder builder = new StringBuilder("Invalid values in map of type ").append(entryValueType).append(": ");
            for (Map.Entry<String, ValueWithType<?>> entry : invalidItems.entrySet()) {
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
        return new MapWithType(Collections.unmodifiableMap(new LinkedHashMap<>(value)), mapType);
    }

    @Override
    public MapType<?> getType() {
        return (MapType<?>) super.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MapWithType)) {
            return false;
        }
        MapWithType other = (MapWithType) obj;
        if (getValue().size() != other.getValue().size() || !Objects.equals(getType(), other.getType())) {
            return false;
        }
        if (!other.getValue().keySet().containsAll(getValue().keySet())
                || !getValue().keySet().containsAll(other.getValue().keySet())) {
            return false;
        }
        for (String key : getValue().keySet()) {
            if (!(Objects.equals(getValue().get(key), other.getValue().get(key)))) {
                return false;
            }
        }
        return true;
    }

    public static final class Builder {

        private final Map<String, ValueWithType<?>> entries = new LinkedHashMap<>();
        private final MapType<?> mapType;

        public Builder(MapType<?> mapType) {
            Objects.requireNonNull(mapType);
            this.mapType = mapType;
        }

        public Builder withEntry(String key, ValueWithType<?> entry) {
            Objects.requireNonNull(key);
            Objects.requireNonNull(entry);
            if (!mapType.getEntryValueType().equals(entry.getType())) {
                throw new IllegalArgumentException("Invalid type specified!");
            }
            entries.put(key, entry);
            return this;
        }

        public MapWithType build() {
            return create(entries, mapType);
        }
    }
}
