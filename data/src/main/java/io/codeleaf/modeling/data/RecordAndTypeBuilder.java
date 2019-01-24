package io.codeleaf.modeling.data;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public final class RecordAndTypeBuilder<T> {

    private final RecordTypeBuilder<RecordType> recordTypeBuilder = RecordTypeBuilder.create();
    private final Map<String, ValueWithType<?>> recordFields = new LinkedHashMap<>();

    private final Function<? super RecordWithType, T> valueWithTypeFunction;

    RecordAndTypeBuilder(Function<? super RecordWithType, T> valueWithTypeFunction) {
        this.valueWithTypeFunction = valueWithTypeFunction;
    }

    public ValueAndTypeBuilder<RecordAndTypeBuilder<T>> field(String fieldName) {
        return requiredField(fieldName);
    }

    public ValueAndTypeBuilder<RecordAndTypeBuilder<T>> requiredField(String fieldName) {
        return field(fieldName, true);
    }

    public ValueAndTypeBuilder<RecordAndTypeBuilder<T>> optionalField(String fieldName) {
        return field(fieldName, false);
    }

    private ValueAndTypeBuilder<RecordAndTypeBuilder<T>> field(String fieldName, boolean required) {
        Objects.requireNonNull(fieldName);
        if (!recordFields.containsKey(fieldName)) {
            throw new IllegalArgumentException("Field already set: " + fieldName);
        }
        return new ValueAndTypeBuilder<>(valueWithType -> {
            recordTypeBuilder.withField(fieldName, required);
            recordFields.put(fieldName, valueWithType);
            return this;
        });
    }

    public T endRecord() {
        return valueWithTypeFunction.apply(RecordWithType.create(recordFields, recordTypeBuilder.endRecord()));
    }

    public static RecordAndTypeBuilder<RecordWithType> create() {
        return new RecordAndTypeBuilder<>(recordWithType -> recordWithType);
    }
}
