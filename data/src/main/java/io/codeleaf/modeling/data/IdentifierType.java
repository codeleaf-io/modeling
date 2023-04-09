package io.codeleaf.modeling.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class IdentifierType implements ScalarType {

    private final String dataType;

    private IdentifierType(String dataType) {
        this.dataType = dataType;
    }

    public static IdentifierType create(String dataType) {
        Objects.requireNonNull(dataType);
        return new IdentifierType(dataType);
    }

    public String getDataType() {
        return dataType;
    }

    @Override
    public Class<?> getValueClass() {
        return String.class;
    }

    @Override
    public List<MalformedValueException> getMalformedCauses(Object value) {
        List<MalformedValueException> causes = new ArrayList<>();
        if (!(value instanceof String)) {
            causes.add(new MalformedValueException(value, "Not of type String!"));
        } else if (((String) value).isEmpty()) {
            causes.add(new MalformedValueException(value, "Not allowed to be empty!"));
        }
        return causes;
    }

    @Override
    public String toString() {
        return String.format("IdentifierType(%s)", dataType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(29, dataType);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IdentifierType &&
                Objects.equals(((IdentifierType) obj).dataType, dataType);
    }

}
