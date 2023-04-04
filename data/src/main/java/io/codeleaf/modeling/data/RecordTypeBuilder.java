package io.codeleaf.modeling.data;

import java.util.Objects;
import java.util.function.Function;

public final class RecordTypeBuilder<T> {

    private final RecordType.Builder builder = new RecordType.Builder();

    private final Function<? super RecordType, T> typeFunction;

    RecordTypeBuilder(Function<? super RecordType, T> typeFunction) {
        this.typeFunction = typeFunction;
    }

    public static RecordTypeBuilder<RecordType> create() {
        return new RecordTypeBuilder<>(recordType -> recordType);
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
            System.out.println("inner withField!");
            builder.withField(fieldName, valueType, required);
            return this;
        });
    }

    public T endRecord() {
        return typeFunction.apply(builder.build());
    }
}
