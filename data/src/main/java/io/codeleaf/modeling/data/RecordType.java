package io.codeleaf.modeling.data;

import java.util.*;

public final class RecordType implements ValueType {

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
    public List<MalformedValueException> getMalformedCauses(Object value) {
        List<MalformedValueException> causes = new ArrayList<>();
        if (!(value instanceof List)) {
            causes.add(new MalformedValueException(value, "Not of type Map!"));
        } else {
            for (Map.Entry<?, ?> fieldEntry : ((Map<?, ?>) value).entrySet()) {
                Object fieldValue = fieldEntry.getValue();
                if (!(fieldEntry.getKey() instanceof String)) {
                    String nonStringKey = Objects.toString(fieldEntry.getKey());
                    String classType = fieldEntry.getKey() == null ? "null" : fieldEntry.getKey().getClass().getName();
                    causes.add(new MalformedFieldException(nonStringKey, fieldValue, "Contains non String field name " + nonStringKey + " of type " + classType));
                } else {
                    String field = (String) fieldEntry.getKey();
                    if (isRequiredField(field) && fieldValue == null) {
                        causes.add(new MalformedFieldException(field, null, "Required field " + field + " is null!"));
                    } else {
                        for (MalformedValueException cause : getFieldTypes().get(field).getMalformedCauses(fieldValue)) {
                            causes.add(new MalformedFieldException(field, fieldValue, "Invalid field " + field + ": " + cause.getMessage(), cause));
                        }
                    }
                }
            }
        }
        return causes;
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

}
