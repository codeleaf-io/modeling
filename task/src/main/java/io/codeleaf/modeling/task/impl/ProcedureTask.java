package io.codeleaf.modeling.task.impl;

import io.codeleaf.modeling.data.MalformedValueException;
import io.codeleaf.modeling.data.ValueType;
import io.codeleaf.modeling.service.Procedure;
import io.codeleaf.modeling.task.Task;

import java.util.*;

public class ProcedureTask<O> implements Task<O> {

    private final Procedure procedure;
    private final Class<O> outputTypeClass;
    private final SortedMap<String, Object> arguments;

    protected ProcedureTask(Procedure procedure, Class<O> outputTypeClass, SortedMap<String, Object> arguments) {
        this.procedure = procedure;
        this.outputTypeClass = outputTypeClass;
        this.arguments = arguments;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public SortedMap<String, Object> getArguments() {
        return arguments;
    }

    @Override
    public Class<O> getOutputTypeClass() {
        return outputTypeClass;
    }

    public static Builder builder(Procedure procedure) {
        Objects.requireNonNull(procedure);
        return new Builder(procedure);
    }

    public static ProcedureTask<?> of(Procedure procedure, Objects... arguments) {
        return builder(procedure).create((Object[]) arguments);
    }

    public static class Builder {

        private final Procedure procedure;
        private final SortedMap<String, Object> arguments = new TreeMap<>();

        private Builder(Procedure procedure) {
            this.procedure = procedure;
        }

        public ProcedureTask<?> create(Object... arguments) {
            int parameterCount = procedure.getParameters().size();
            if (arguments == null) {
                if (parameterCount == 0) {
                    return build();
                } else {
                    throw new IllegalArgumentException("Incorrect number of arguments given!");
                }
            }
            if (arguments.length != parameterCount) {
                throw new IllegalArgumentException("Incorrect number of arguments given!");
            }
            int i = 0;
            List<MalformedValueException> exceptions = new ArrayList<>();
            SortedMap<String, Object> values = new TreeMap<>();
            for (Map.Entry<String, ValueType> parameter : procedure.getParameters().entrySet()) {
                Object value = arguments[i++];
                exceptions.addAll(parameter.getValue().getMalformedCauses(value));
                values.put(parameter.getKey(), value);
            }
            MalformedValueException.isWellFormed(exceptions);
            SortedMap<String, Object> existing = new TreeMap<>(this.arguments);
            this.arguments.putAll(values);
            ProcedureTask<?> task = build();
            this.arguments.clear();
            this.arguments.putAll(existing);
            return task;
        }

        public Builder withArgument(String argumentName, Object argumentValue) {
            Objects.requireNonNull(argumentName);
            if (!procedure.getParameters().containsKey(argumentName)) {
                throw new NoSuchElementException("No argument with name: " + argumentName);
            }
            procedure.getParameters().get(argumentName).ensureWellFormed(argumentValue);
            arguments.put(argumentName, argumentValue);
            return this;
        }

        public ProcedureTask<?> build() {
            for (Map.Entry<String, Object> value : arguments.entrySet()) {
                String argumentKey = value.getKey();
                if (!procedure.getParameters().containsKey(argumentKey)) {
                    throw new NoSuchElementException("No argument with name: " + argumentKey);
                }
                procedure.getParameters().get(argumentKey).ensureWellFormed(value.getValue());
            }
            return new ProcedureTask<>(procedure,
                    procedure.getReturnType().getValueClass(),
                    Collections.unmodifiableSortedMap(new TreeMap<>(arguments)));
        }
    }
}
