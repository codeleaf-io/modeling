package io.codeleaf.modeling.service;

import io.codeleaf.modeling.data.ValueType;

import java.util.Collections;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

public final class Procedure {

    private final String name;
    private final SortedMap<String, ValueType> parameters;
    private final ValueType returnType;

    private Procedure(String name, SortedMap<String, ValueType> parameters, ValueType returnType) {
        this.name = name;
        this.parameters = parameters;
        this.returnType = returnType;
    }

    public String getName() {
        return name;
    }

    public SortedMap<String, ValueType> getParameters() {
        return parameters;
    }

    public ValueType getReturnType() {
        return returnType;
    }

    public static final class Builder {

        private final SortedMap<String, ValueType> parameters = new TreeMap<>();
        private String name;
        private ValueType returnType;

        public Builder withName(String name) {
            Objects.requireNonNull(name);
            if (name.isEmpty()) {
                throw new IllegalArgumentException("No empty name allowed!");
            }
            this.name = name;
            return this;
        }

        public Builder withParameter(String parameterName, ValueType parameterType) {
            Objects.requireNonNull(parameterName);
            if (parameterName.isEmpty()) {
                throw new IllegalArgumentException("No empty parameterName allowed!");
            }
            Objects.requireNonNull(parameterType);
            parameters.put(parameterName, parameterType);
            return this;
        }

        public Builder withReturnType(ValueType returnType) {
            this.returnType = returnType;
            return this;
        }

        private void validate() {
            if (name == null || name.isEmpty()) {
                throw new IllegalStateException("Name must be set!");
            }
        }

        public Procedure build() {
            validate();
            return new Procedure(name, Collections.unmodifiableSortedMap(new TreeMap<>(parameters)), returnType);
        }
    }

}
