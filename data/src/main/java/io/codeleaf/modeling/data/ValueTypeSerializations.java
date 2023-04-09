package io.codeleaf.modeling.data;

import io.codeleaf.common.utils.StringEncodings;

import java.util.*;

public final class ValueTypeSerializations {

    public static String serialize(ValueType valueType) {
        Objects.requireNonNull(valueType);
        Map<String, String> value = new LinkedHashMap<>();
        if (valueType instanceof BinaryType) {
            value.put("type", "byte");
        } else if (valueType instanceof BooleanType) {
            value.put("type", "boolean");
        } else if (valueType instanceof EnumType) {
            value.put("type", "enum");
            value.put("options", StringEncodings.encodeSet(((EnumType) valueType).getValues()));
        } else if (valueType instanceof FloatType) {
            value.put("type", "float");
        } else if (valueType instanceof IdentifierType) {
            value.put("type", "identifier");
            value.put("dataType", ((IdentifierType) valueType).getDataType());
        } else if (valueType instanceof IntegerType) {
            value.put("type", "integer");
        } else if (valueType instanceof ListType) {
            value.put("type", "list");
            value.put("itemType", serialize(((ListType<?>) valueType).getItemValueType()));
        } else if (valueType instanceof MapType) {
            value.put("type", "map");
            value.put("entryType", serialize(((MapType<?>) valueType).getEntryValueType()));
        } else if (valueType instanceof RecordType) {
            value.put("type", "record");
            RecordType recordType = (RecordType) valueType;
            Map<String, String> fieldTypes = new LinkedHashMap<>();
            Set<String> requiredFields = new LinkedHashSet<>();
            for (Map.Entry<String, ValueType> fieldType : recordType.getFieldTypes().entrySet()) {
                fieldTypes.put(fieldType.getKey(), serialize(fieldType.getValue()));
                if (recordType.isRequiredField(fieldType.getKey())) {
                    requiredFields.add(fieldType.getKey());
                }
            }
            value.put("fieldTypes", StringEncodings.encodeMap(fieldTypes));
            value.put("requiredFields", StringEncodings.encodeSet(requiredFields));
        } else if (valueType instanceof TextType) {
            value.put("type", "text");
        } else if (valueType instanceof TimestampType) {
            value.put("type", "timestamp");
        }
        return StringEncodings.encodeMap(value);
    }

    public static ValueType deserialize(String serializedValueType) {
        Objects.requireNonNull(serializedValueType);
        Map<String, String> value = StringEncodings.decodeMap(serializedValueType);
        ValueType valueType;
        String type = value.get("type");
        if (Objects.equals("byte", type)) {
            valueType = BinaryWithType.TYPE;
        } else if (Objects.equals("boolean", type)) {
            valueType = BooleanWithType.TYPE;
        } else if (Objects.equals("enum", type)) {
            valueType = EnumType.create(StringEncodings.decodeSet(value.get("options")));
        } else if (Objects.equals("float", type)) {
            valueType = FloatWithType.TYPE;
        } else if (Objects.equals("identifier", type)) {
            valueType = IdentifierType.create(value.get("dataType"));
        } else if (Objects.equals("integer", type)) {
            valueType = IntegerWithType.TYPE;
        } else if (Objects.equals("list", type)) {
            valueType = ListType.create(deserialize(value.get("itemType")));
        } else if (Objects.equals("map", type)) {
            valueType = MapType.create(deserialize(value.get("entryType")));
        } else if (Objects.equals("record", type)) {
            RecordType.Builder builder = new RecordType.Builder();
            Map<String, String> fieldTypes = StringEncodings.decodeMap(value.get("fieldTypes"));
            Set<String> requiredFields = StringEncodings.decodeSet(value.get("requiredFields"));
            for (Map.Entry<String, String> field : fieldTypes.entrySet()) {
                String name = field.getKey();
                builder.withField(name, deserialize(fieldTypes.get(name)), requiredFields.contains(name));
            }
            valueType = builder.build();
        } else if (Objects.equals("text", type)) {
            valueType = TextWithType.TYPE;
        } else if (Objects.equals("timestamp", type)) {
            valueType = TimestampWithType.TYPE;
        } else {
            throw new IllegalArgumentException("Unknown type: " + type);
        }
        return valueType;
    }
}
