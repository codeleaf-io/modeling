package io.codeleaf.modeling.data;

import java.util.*;

public final class RecordType implements ValueType {

    public static final class Builder {

        private final Map<String, ValueType> fieldTypes = new LinkedHashMap<>();
        private final Map<String, Boolean> requiredFields = new HashMap<>();

        public Builder withRequiredField(String name, ValueType valueType) {
            return withField(name, valueType, true);
        }

        public Builder withOptionalField(String name, ValueType valueType) {
            return withField(name, valueType, false);
        }

        public Builder withField(String name, ValueType valueType, boolean required) {
            if (fieldTypes.containsKey(name)) {
                throw new IllegalStateException("Already contains field: " + name);
            }
            fieldTypes.put(name, valueType);
            requiredFields.put(name, required);
            return this;
        }

        public RecordType build() {
            return new RecordType(
                    Collections.unmodifiableMap(new LinkedHashMap<>(fieldTypes)),
                    Collections.unmodifiableMap(new HashMap<>(requiredFields)));
        }

    }

    private final Map<String, ValueType> fieldTypes;
    private final Map<String, Boolean> requiredFields;

    private RecordType(Map<String, ValueType> fieldTypes, Map<String, Boolean> requiredFields) {
        this.fieldTypes = fieldTypes;
        this.requiredFields = requiredFields;
    }

    public Map<String, ValueType> getFieldTypes() {
        return fieldTypes;
    }

    public boolean isRequiredField(String fieldName) {
        if (!requiredFields.containsKey(fieldName)) {
            throw new IllegalArgumentException("No such field present: " + fieldName);
        }
        return requiredFields.get(fieldName);
    }

    @Override
    public Class<?> getValueClass() {
        return Map.class;
    }

    @Override
    public String toString() {
        return String.format("RecordType(%s, %s)", fieldTypes, requiredFields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(57, fieldTypes, requiredFields);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RecordType)) {
            return false;
        }
        RecordType other = (RecordType) obj;
        if (fieldTypes.size() != other.fieldTypes.size()) {
            return false;
        }
        for (String fieldName : fieldTypes.keySet()) {
            if (!Objects.equals(fieldTypes.get(fieldName), other.fieldTypes.get(fieldName))
                || requiredFields.get(fieldName) != other.requiredFields.get(fieldName)) {
                return false;
            }
        }
        return true;
    }

}
