package io.codeleaf.modeling.task.impl;

import io.codeleaf.modeling.task.Task;

import java.util.function.Function;

public final class FunctionTaskHandler<T extends Task<O>, O> extends SingleTaskHandler<T, O> {

    private final Function<T, O> function;

    public FunctionTaskHandler(Class<T> taskClass, Function<T, O> function) {
        super(taskClass);
        this.function = function;
    }

    @Override
    protected O doExecuteTask(T task) {
        return function.apply(task);
    }
}
