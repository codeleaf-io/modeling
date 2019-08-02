package io.codeleaf.modeling.data;

import java.util.ArrayList;
import java.util.List;

public final class BooleanType implements ValueType {

    @Override
    public Class<?> getValueClass() {
        return Boolean.class;
    }

    @Override
    public List<MalformedValueException> getMalformedCauses(Object value) {
        List<MalformedValueException> causes = new ArrayList<>();
        if (!(value instanceof Boolean)) {
            causes.add(new MalformedValueException(value, "Not true or false!"));
        }
        return causes;
    }

    @Override
    public String toString() {
        return "BooleanType";
    }

    @Override
    public int hashCode() {
        return 97;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BooleanType;
    }

}
