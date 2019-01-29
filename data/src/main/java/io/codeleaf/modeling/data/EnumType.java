package io.codeleaf.modeling.data;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public final class EnumType implements ValueType {

    public static EnumType create(Set<String> values) {
        Objects.requireNonNull(values);
        Set<String> copy = new LinkedHashSet<>();
        for (String value : values) {
            if (value == null) {
                throw new IllegalArgumentException("No null values allowed!");
            }
            copy.add(value);
        }
        return new EnumType(Collections.unmodifiableSet(copy));
    }

    private final Set<String> values;

    private EnumType(Set<String> values) {
        this.values = values;
    }

    public Set<String> getValues() {
        return values;
    }

    @Override
    public Class<?> getValueClass() {
        return String.class;
    }

    @Override
    public String toString() {
        return String.format("EnumType(%s)", values);
    }

    @Override
    public int hashCode() {
        return 27521 + Objects.hash(values);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EnumType)) {
            return false;
        }
        EnumType other = (EnumType) obj;
        if (other.values.size() != values.size()) {
            return false;
        }
        return values.containsAll(other.values);
    }
}