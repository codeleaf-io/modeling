package io.codeleaf.modeling.data;

import java.util.*;

public final class RecordWithType extends ValueWithType<Map<String, ValueWithType<?>>> {

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

    public static RecordWithType create(Map<String, ValueWithType<?>> value, RecordType recordType) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(recordType);
        Set<String> undefinedFieldNames = new HashSet<>(value.keySet());
        undefinedFieldNames.removeAll(recordType.getFieldTypes().keySet());
        Set<String> missingFieldNames = new HashSet<>();
        Set<String> invalidFieldNames = new HashSet<>();
        for (String fieldName : recordType.getFieldTypes().keySet()) {
            ValueWithType<?> fieldValue = value.get(fieldName);
            if (fieldValue != null) {
                ValueType fieldType = recordType.getFieldTypes().get(fieldName);
                if (!fieldType.equals(fieldValue.getType())) {
                    invalidFieldNames.add(fieldName);
                }
            } else {
                if (recordType.isRequiredField(fieldName)) {
                    missingFieldNames.add(fieldName);
                }
            }
        }
        if (!undefinedFieldNames.isEmpty() || !missingFieldNames.isEmpty() || !invalidFieldNames.isEmpty()) {
            StringBuilder builder = new StringBuilder("Invalid value: ");
            for (String fieldName : undefinedFieldNames) {
                builder.append(fieldName).append(": not defined in type, ");
            }
            for (String fieldName : missingFieldNames) {
                builder.append(fieldName).append(": missing in value, ");
            }
            for (String fieldName : invalidFieldNames) {
                builder.append(fieldName).append(String.format(": invalid type [%s expected, %s found], ",
                        recordType.getFieldTypes().get(fieldName),
                        value.get(fieldName).getType()));
            }
            builder.setLength(builder.length() - 2);
            throw new IllegalArgumentException(builder.toString());
        }
        return new RecordWithType(Collections.unmodifiableMap(new LinkedHashMap<>(value)), recordType);
    }

    public static final class Builder {

        private final Map<String, ValueWithType<?>> fields = new LinkedHashMap<>();

        private final RecordType recordType;

        public Builder(RecordType recordType) {
            Objects.requireNonNull(recordType);
            this.recordType = recordType;
        }

        public Builder withField(String fieldName, ValueWithType<?> valueWithType) {
            Objects.requireNonNull(fieldName);
            Objects.requireNonNull(valueWithType);
            if (!recordType.getFieldTypes().containsKey(fieldName)) {
                throw new IllegalArgumentException("No such field defined: " + fieldName);
            }
            if (!recordType.getFieldTypes().get(fieldName).equals(valueWithType.getType())) {
                throw new IllegalArgumentException(String.format("Invalid type %s needed but %s provided",
                        recordType.getFieldTypes().get(fieldName),
                        valueWithType.getType()));
            }
            fields.put(fieldName, valueWithType);
            return this;
        }

        public RecordWithType build() {
            return RecordWithType.create(fields, recordType);
        }
    }
}
