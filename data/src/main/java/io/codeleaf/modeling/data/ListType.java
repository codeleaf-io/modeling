package io.codeleaf.modeling.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class ListType<I extends ValueType> implements ValueType {

    private final I itemValueType;

    private ListType(I itemValueType) {
        this.itemValueType = itemValueType;
    }

    public static <I extends ValueType> ListType<I> create(I itemValueType) {
        Objects.requireNonNull(itemValueType);
        return new ListType<>(itemValueType);
    }

    public I getItemValueType() {
        return itemValueType;
    }

    @Override
    public Class<?> getValueClass() {
        return List.class;
    }

    @Override
    public List<MalformedValueException> getMalformedCauses(Object value) {
        List<MalformedValueException> causes = new ArrayList<>();
        if (!(value instanceof List)) {
            causes.add(new MalformedValueException(value, "Not of type List!"));
        } else {
            List<?> list = ((List<?>) value);
            for (int index = 0; index < list.size(); index++) {
                Object item = list.get(index);
                for (MalformedValueException cause : itemValueType.getMalformedCauses(item)) {
                    causes.add(new MalformedItemException(index, item, "Invalid list item: " + cause.getMessage(), cause));
                }
            }
        }
        return causes;
    }

    @Override
    public String toString() {
        return String.format("ListType(%s)", itemValueType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(71, itemValueType);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListType &&
                Objects.equals(((ListType<?>) obj).itemValueType, itemValueType);
    }

}
