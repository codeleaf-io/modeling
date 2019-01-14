package io.codeleaf.modeling.data;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public final class RecordWithType extends ValueWithType<Map<String, ValueWithType<?>>> {

    public static RecordWithType create(Map<String, ValueWithType<?>> value, RecordType recordType) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(recordType);
        return new RecordWithType(Collections.unmodifiableMap(value), recordType);
    }

    private RecordWithType(Map<String, ValueWithType<?>> value, RecordType recordType) {
        super(value, recordType);
    }

    @Override
    public RecordType getType() {
        return (RecordType) super.getType();
    }

    @Override
    public int hashCode() {
        int hash = 563201 + Objects.hashCode(getType());
        for (Map.Entry<String, ValueWithType<?>> entry : getValue().entrySet()) {
            hash += Objects.hash(entry.getKey(), entry.getValue());
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RecordWithType)) {
            return false;
        }
        RecordWithType other = (RecordWithType) obj;
        if (getValue().size() != other.getValue().size() || !Objects.equals(getType(), other.getType())) {
            return false;
        }
        for (String fieldName : getValue().keySet()) {
            if (!other.getValue().containsKey(fieldName) || !(Objects.equals(getValue().get(fieldName), other.getValue().get(fieldName)))) {
                return false;
            }
        }
        return true;
    }

}
