package io.codeleaf.modeling.data;

import java.util.Objects;

public final class IdentifierType implements ValueType {

    public static IdentifierType create(String dataType) {
        Objects.requireNonNull(dataType);
        return new IdentifierType(dataType);
    }

    private final String dataType;

    private IdentifierType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataType() {
        return dataType;
    }

    @Override
    public Class<?> getValueClass() {
        return String.class;
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
