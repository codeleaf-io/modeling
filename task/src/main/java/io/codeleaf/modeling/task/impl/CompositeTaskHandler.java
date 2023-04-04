package io.codeleaf.modeling.task.impl;

import io.codeleaf.modeling.task.Task;
import io.codeleaf.modeling.task.TaskHandlingException;
import io.codeleaf.modeling.task.TaskHandler;
import io.codeleaf.modeling.task.UnsupportedTaskException;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public final class CompositeTaskHandler implements TaskHandler {

    private final Map<Class<? extends Task<?>>, TaskHandler> taskHandlers;

    public CompositeTaskHandler(Map<Class<? extends Task<?>>, TaskHandler> taskHandlers) {
        this.taskHandlers = taskHandlers;
    }

    @Override
    public <T extends Task<?>> boolean supportsTaskType(Class<T> taskTypeClass) {
        return taskHandlers.containsKey(taskTypeClass);
    }

    @Override
    public <O> O handleTask(Task<O> task) throws TaskHandlingException {
        Objects.requireNonNull(task);
        TaskHandler taskHandler = taskHandlers.get(task.getClass());
        if (taskHandler == null) {
            throw new UnsupportedTaskException(task, this);
        }
        return taskHandler.handleTask(task);
    }

    public static final class Builder {

        private final Map<Class<? extends Task<?>>, TaskHandler> taskHandlers = new LinkedHashMap<>();

        public Builder withTaskHandler(Class<? extends Task<?>> taskClass, TaskHandler taskHandler) {
            Objects.requireNonNull(taskClass);
            Objects.requireNonNull(taskHandler);
            if (taskHandlers.containsKey(taskClass)) {
                throw new IllegalStateException("Already TaskHandler registered for task class: " + taskClass);
            }
            taskHandlers.put(taskClass, taskHandler);
            return this;
        }

        public CompositeTaskHandler build() {
            return new CompositeTaskHandler(Collections.unmodifiableMap(new LinkedHashMap<>(taskHandlers)));
        }

    }
}
