package io.codeleaf.modeling.data;

import java.util.Objects;
import java.util.function.Function;

public final class RecordWithTypeBuilder<T> {

    private final RecordType recordType;
    private final Function<? super RecordWithType, T> valueWithTypeFunction;
    private final RecordWithType.Builder builder;

    RecordWithTypeBuilder(RecordType recordType, Function<? super RecordWithType, T> valueWithTypeFunction) {
        this.recordType = recordType;
        this.valueWithTypeFunction = valueWithTypeFunction;
        builder = new RecordWithType.Builder(recordType);
    }

    public ValueWithTypeBuilder<RecordWithTypeBuilder<T>> field(String fieldName) {
        Objects.requireNonNull(fieldName);
        if (!recordType.getFieldTypes().containsKey(fieldName)) {
            throw new IllegalArgumentException("Invalid fieldName: " + fieldName);
        }
        return new ValueWithTypeBuilder<>(recordType.getFieldTypes().get(fieldName), valueWithType -> {
            builder.withField(fieldName, valueWithType);
            return this;
        });
    }

    public T endRecord() {
        return valueWithTypeFunction.apply(builder.build());
    }

    public static RecordWithTypeBuilder<RecordWithType> create(RecordType recordType) {
        Objects.requireNonNull(recordType);
        return new RecordWithTypeBuilder<>(recordType, recordWithType -> recordWithType);
    }
}
