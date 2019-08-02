package io.codeleaf.modeling.data;

import java.util.ArrayList;
import java.util.List;

public final class TimestampType implements ValueType {

    @Override
    public Class<?> getValueClass() {
        return Long.class;
    }

    @Override
    public List<MalformedValueException> getMalformedCauses(Object value) {
        List<MalformedValueException> causes = new ArrayList<>();
        if (!(value instanceof Long)) {
            causes.add(new MalformedValueException(value, "Not of type Long!"));
        }
        return causes;
    }

    @Override
    public String toString() {
        return "TimestampType";
    }

    @Override
    public int hashCode() {
        return 37;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TimestampType;
    }

}
