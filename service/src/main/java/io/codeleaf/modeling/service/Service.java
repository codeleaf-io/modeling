package io.codeleaf.modeling.service;

import io.codeleaf.modeling.data.ValueType;

import java.util.*;

public final class Service {

    private final String name;
    private final Set<Procedure> procedures;
    private Service(String name, Set<Procedure> procedures) {
        this.name = name;
        this.procedures = procedures;
    }

    public String getName() {
        return name;
    }

    public Set<Procedure> getProcedures() {
        return procedures;
    }

    public Procedure getProcedure(String name, ValueType... parameterType) throws NoSuchMethodException {
        return getProcedure(name, Arrays.asList(parameterType));
    }

    public Procedure getProcedure(String name, List<ValueType> parameterTypes) throws NoSuchMethodException {
        for (Procedure procedure : procedures) {
            if (procedure.getName().equals(name)) {
                if (parametersMatches(procedure.getParameters().values(), parameterTypes)) {
                    return procedure;
                }
            }
        }
        throw new NoSuchMethodException();
    }

    private boolean parametersMatches(Collection<ValueType> values, List<ValueType> parameterTypes) {
        if (parameterTypes == null || parameterTypes.isEmpty()) {
            return values == null || values.isEmpty();
        }
        if (values.size() != parameterTypes.size()) {
            return false;
        }
        List<ValueType> valuesList = new ArrayList<>(values);
        for (int i = 0; i < valuesList.size(); i++) {
            if (!Objects.equals(valuesList.get(i), parameterTypes.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static final class Builder {

        private final Set<Procedure> procedures = new LinkedHashSet<>();
        private String name;

        public Builder withName(String name) {
            Objects.requireNonNull(name);
            if (name.isEmpty()) {
                throw new IllegalArgumentException("No empty name allowed!");
            }
            this.name = name;
            return this;
        }

        public Builder withProcedure(Procedure... procedure) {
            Objects.requireNonNull(procedure);
            for (int i = 0; i < procedure.length; i++) {
                Objects.requireNonNull(procedure[i]);
                procedures.add(procedure[i]);
            }
            return this;
        }

        private void validate() {
            if (name == null) {
                throw new IllegalStateException("Name must be set!");
            }
            if (procedures.isEmpty()) {
                throw new IllegalStateException("Must have at least 1 procedure defined!");
            }
        }

        public Service build() {
            validate();
            return new Service(name, Collections.unmodifiableSet(new LinkedHashSet<>(procedures)));
        }

    }

}
