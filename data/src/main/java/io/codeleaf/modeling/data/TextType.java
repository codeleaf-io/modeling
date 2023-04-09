package io.codeleaf.modeling.data;

import java.util.ArrayList;
import java.util.List;

public final class TextType implements ScalarType {

    @Override
    public Class<?> getValueClass() {
        return String.class;
    }

    @Override
    public List<MalformedValueException> getMalformedCauses(Object value) {
        List<MalformedValueException> causes = new ArrayList<>();
        if (!(value instanceof String)) {
            causes.add(new MalformedValueException(value, "Not of type String!"));
        }
        return causes;
    }

    @Override
    public String toString() {
        return "TextType";
    }

    @Override
    public int hashCode() {
        return 61;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TextType;
    }

}
