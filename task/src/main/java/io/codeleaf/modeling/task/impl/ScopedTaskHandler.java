package io.codeleaf.modeling.task.impl;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.task.Task;
import io.codeleaf.modeling.task.TaskHandler;
import io.codeleaf.modeling.task.TaskHandlingException;

import java.util.Objects;
import java.util.function.Function;

public final class ScopedTaskHandler<T extends Task<?>> implements TaskHandler {

    private final TaskHandler taskHandler;
    private final Class<T> baseTaskType;

    private ScopedTaskHandler(Class<T> baseTaskType, TaskHandler taskHandler) {
        this.taskHandler = taskHandler;
        this.baseTaskType = baseTaskType;
    }

    private static TaskHandler wrap(Function<?, ?> function) {
        Objects.requireNonNull(function);
        return new TaskHandler() {
            @Override
            public <TL extends Task<?>> boolean supportsTaskType(Class<TL> taskTypeClass) {
                return true;
            }

            @Override
            public <OL> OL handleTask(Task<OL> task) throws TaskHandlingException {
                try {
                    return Types.cast(function.apply(Types.cast(task)));
                } catch (RuntimeException cause) {
                    throw new TaskHandlingException(task, "Failed to handle task by function: " + cause, cause);
                }
            }
        };
    }

    public static ScopedTaskHandler<Task<?>> create(Function<Task<?>, ?> function) {
        Objects.requireNonNull(function);
        return create(Types.cast(Task.class), function);
    }

    public static <T extends Task<?>> ScopedTaskHandler<T> create(Class<T> baseTaskType, Function<T, ?> function) {
        Objects.requireNonNull(function);
        return create(baseTaskType, wrap(function));
    }

    public static <T extends Task<?>> ScopedTaskHandler<T> create(Class<T> baseTaskType, TaskHandler taskHandler) {
        Objects.requireNonNull(baseTaskType);
        Objects.requireNonNull(taskHandler);
        return new ScopedTaskHandler<>(baseTaskType, taskHandler);
    }

    @Override
    public <TL extends Task<?>> boolean supportsTaskType(Class<TL> taskTypeClass) {
        return baseTaskType.isAssignableFrom(taskTypeClass) && taskHandler.supportsTaskType(taskTypeClass);
    }

    @Override
    public <OL> OL handleTask(Task<OL> task) throws TaskHandlingException {
        return taskHandler.handleTask(task);
    }
}
