package io.codeleaf.modeling.selection;

import java.util.*;

public final class InSelection<T> implements Selection {

    @SafeVarargs
    public static <T> InSelection<T> create(T... values) {
        Objects.requireNonNull(values);
        if (values.length == 0) {
            throw new IllegalArgumentException("At least 1 value must be defined!");
        }
        return create(Arrays.asList(values));
    }

    public static <T> InSelection<T> create(Collection<T> values) {
        Objects.requireNonNull(values);
        return new InSelection<>(Collections.unmodifiableSet(new LinkedHashSet<>(values)));
    }

    private final Set<T> values;

    private InSelection(Set<T> values) {
        this.values = values;
    }

    public Set<T> getValues() {
        return values;
    }

}
