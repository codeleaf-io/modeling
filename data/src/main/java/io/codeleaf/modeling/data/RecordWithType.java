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

}
