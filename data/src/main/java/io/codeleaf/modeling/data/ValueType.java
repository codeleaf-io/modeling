package io.codeleaf.modeling.data;

import java.util.List;

public interface ValueType {

    Class<?> getValueClass();

    default boolean isWellFormed(Object value) {
        return getMalformedCauses(value).isEmpty();
    }

    default void ensureWellFormed(Object value) throws MalformedValueException {
        MalformedValueException.isWellFormed(getMalformedCauses(value));
    }

    List<MalformedValueException> getMalformedCauses(Object value);

}
