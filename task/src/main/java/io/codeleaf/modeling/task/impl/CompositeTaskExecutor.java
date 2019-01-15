package io.codeleaf.modeling.task.impl;

import io.codeleaf.modeling.task.Task;
import io.codeleaf.modeling.task.TaskExecutionException;
import io.codeleaf.modeling.task.TaskExecutor;
import io.codeleaf.modeling.task.UnsupportedTaskException;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public final class CompositeTaskExecutor implements TaskExecutor {

    public static final class Builder {

        private final Map<Class<? extends Task<?>>, TaskExecutor> taskExecutors = new LinkedHashMap<>();

        public Builder withTaskExecutor(Class<? extends Task<?>> taskClass, TaskExecutor taskExecutor) {
            Objects.requireNonNull(taskClass);
            Objects.requireNonNull(taskExecutor);
            if (taskExecutors.containsKey(taskClass)) {
                throw new IllegalStateException("Already TaskExecutor registered for task class: " + taskClass);
            }
            taskExecutors.put(taskClass, taskExecutor);
            return this;
        }

        public CompositeTaskExecutor build() {
            return new CompositeTaskExecutor(Collections.unmodifiableMap(new LinkedHashMap<>(taskExecutors)));
        }

    }

    private final Map<Class<? extends Task<?>>, TaskExecutor> taskExecutors;

    public CompositeTaskExecutor(Map<Class<? extends Task<?>>, TaskExecutor> taskExecutors) {
        this.taskExecutors = taskExecutors;
    }

    @Override
    public <T extends Task<?>> boolean supportsTaskType(Class<T> taskTypeClass) {
        return taskExecutors.containsKey(taskTypeClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <O> O executeTask(Task<O> task) throws TaskExecutionException {
        Objects.requireNonNull(task);
        TaskExecutor taskExecutor = taskExecutors.get((Class<? extends Task<?>>) task.getClass());
        if (taskExecutor == null) {
            throw new UnsupportedTaskException(task, this);
        }
        return taskExecutor.executeTask(task);
    }

}
