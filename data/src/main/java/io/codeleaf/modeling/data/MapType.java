package io.codeleaf.modeling.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class MapType<E extends ValueType> implements ValueType {

    private final E entryValueType;

    private MapType(E entryValueType) {
        this.entryValueType = entryValueType;
    }

    public static <I extends ValueType> MapType<I> create(I entryValueType) {
        Objects.requireNonNull(entryValueType);
        return new MapType<>(entryValueType);
    }

    public E getEntryValueType() {
        return entryValueType;
    }

    @Override
    public Class<?> getValueClass() {
        return Map.class;
    }

    @Override
    public List<MalformedValueException> getMalformedCauses(Object value) {
        List<MalformedValueException> causes = new ArrayList<>();
        if (!(value instanceof Map)) {
            causes.add(new MalformedValueException(value, "Not of type Map!"));
        } else {
            Map<?, ?> list = ((Map<?, ?>) value);
            for (Map.Entry<?, ?> entry : list.entrySet()) {
                Object key = entry.getKey();
                Object entryValue = entry.getValue();
                if (!(key instanceof String)) {
                    causes.add(new MalformedEntryException(String.valueOf(key), entryValue, "Invalid map entry: key is not a String!"));

                }
                for (MalformedValueException cause : entryValueType.getMalformedCauses(entryValue)) {
                    causes.add(new MalformedEntryException(String.valueOf(key), entryValue, "Invalid map entry: " + cause.getMessage(), cause));
                }
            }
        }
        return causes;
    }

    @Override
    public String toString() {
        return String.format("MapType(%s)", entryValueType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(97, entryValueType);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MapType &&
                Objects.equals(((MapType<?>) obj).entryValueType, entryValueType);
    }

}
