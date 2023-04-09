package io.codeleaf.modeling.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class BinaryType implements ScalarType {

    @Override
    public Class<?> getValueClass() {
        return byte[].class;
    }

    @Override
    public List<MalformedValueException> getMalformedCauses(Object value) {
        List<MalformedValueException> causes = new ArrayList<>();
        if (!Objects.equals(byte[].class, value.getClass())) {
            causes.add(new MalformedValueException(value, "Not a byte[]!"));
        }
        return causes;
    }

    @Override
    public String toString() {
        return "BinaryType";
    }

    @Override
    public int hashCode() {
        return 8451;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BinaryType;
    }

}
