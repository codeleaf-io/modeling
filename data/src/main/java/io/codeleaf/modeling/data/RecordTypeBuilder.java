package io.codeleaf.modeling.data;

import java.util.Objects;
import java.util.function.Function;

public final class RecordTypeBuilder<T> {

    private final RecordType.Builder builder = new RecordType.Builder();

    private final Function<ValueType, T> typeFunction;

    RecordTypeBuilder(Function<ValueType, T> typeFunction) {
        this.typeFunction = typeFunction;
    }

    public ValueTypeBuilder<RecordTypeBuilder<T>> withRequiredField(String fieldName) {
        return withField(fieldName, true);
    }

    public ValueTypeBuilder<RecordTypeBuilder<T>> withOptionalField(String fieldName) {
        return withField(fieldName, false);
    }

    public ValueTypeBuilder<RecordTypeBuilder<T>> withField(String fieldName, boolean required) {
        Objects.requireNonNull(fieldName);
        if (fieldName.isEmpty()) {
            throw new IllegalArgumentException("Field name is not allowed to be empty!");
        }
        return new ValueTypeBuilder<>(valueType -> {
            builder.withField(fieldName, valueType, required);
            return this;
        });
    }

    public T endRecord() {
        return typeFunction.apply(builder.build());
    }

    public static RecordTypeBuilder<RecordType> beginRecord() {
        return new RecordTypeBuilder<>(valueType -> (RecordType) valueType);
    }

}
