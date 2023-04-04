package io.codeleaf.modeling.data;

import java.util.ArrayList;
import java.util.List;

public final class FloatType implements ValueType {

    @Override
    public Class<?> getValueClass() {
        return Double.class;
    }

    @Override
    public List<MalformedValueException> getMalformedCauses(Object value) {
        List<MalformedValueException> causes = new ArrayList<>();
        if (!(value instanceof Double)) {
            causes.add(new MalformedValueException(value, "Not of type Double!"));
        }
        return causes;
    }

    @Override
    public String toString() {
        return "FloatType";
    }

    @Override
    public int hashCode() {
        return 37;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FloatType;
    }

}
