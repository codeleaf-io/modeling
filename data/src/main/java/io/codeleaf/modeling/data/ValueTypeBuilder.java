package io.codeleaf.modeling.data;

import java.util.function.Function;

public final class ValueTypeBuilder<T> {

    private final Function<ValueType, T> typeFunction;

    ValueTypeBuilder(Function<ValueType, T> typeFunction) {
        this.typeFunction = typeFunction;
    }

    public static ValueTypeBuilder<ValueType> create() {
        return new ValueTypeBuilder<>(valueType -> valueType);
    }

    public RecordTypeBuilder<T> beginRecord() {
        return new RecordTypeBuilder<>(typeFunction);
    }

    public ValueTypeBuilder<T> list() {
        return new ValueTypeBuilder<>(valueType -> typeFunction.apply(ListType.create(valueType)));
    }

    public ValueTypeBuilder<T> map() {
        return new ValueTypeBuilder<>(valueType -> typeFunction.apply(MapType.create(valueType)));
    }

    public T bool() {
        return typeFunction.apply(BooleanWithType.TYPE);
    }

    public EnumTypeBuilder<T> beginEnum() {
        return new EnumTypeBuilder<>(typeFunction);
    }

    public T floatingPoint() {
        return typeFunction.apply(FloatWithType.TYPE);
    }

    public T identifier(String dataType) {
        return typeFunction.apply(IdentifierType.create(dataType));
    }

    public T integer() {
        return typeFunction.apply(IntegerWithType.TYPE);
    }

    public T text() {
        return typeFunction.apply(TextWithType.TYPE);
    }

    public T timestamp() {
        return typeFunction.apply(TimestampWithType.TYPE);
    }

}
