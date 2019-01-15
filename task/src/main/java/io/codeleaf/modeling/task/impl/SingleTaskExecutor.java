package io.codeleaf.modeling.task.impl;

import io.codeleaf.modeling.task.Task;
import io.codeleaf.modeling.task.TaskExecutionException;
import io.codeleaf.modeling.task.TaskExecutor;
import io.codeleaf.modeling.task.UnsupportedTaskException;

import java.util.Objects;

public abstract class SingleTaskExecutor<T extends Task<O>, O> implements TaskExecutor {

    private final Class<T> taskClass;

    public SingleTaskExecutor(Class<T> taskClass) {
        this.taskClass = taskClass;
    }

    public Class<T> getSupportedTaskClass() {
        return taskClass;
    }

    @Override
    public <T1 extends Task<?>> boolean supportsTaskType(Class<T1> taskTypeClass) {
        return Objects.equals((Class<?>) taskClass, (Class<?>) taskTypeClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <O1> O1 executeTask(Task<O1> task) throws TaskExecutionException {
        Objects.requireNonNull(task);
        if (!supportsTaskType(task.getClass())) {
            throw new UnsupportedTaskException(task, this);
        }
        try {
            return (O1) doExecuteTask((T) task);
        } catch (TaskExecutionException cause) {
            if (task.equals(cause.getTask())) {
                throw cause;
            } else {
                throw new TaskExecutionException(task, "Failed to execute task because of a child task: " + cause.getMessage(), cause);
            }
        } catch (Exception cause) {
            throw new TaskExecutionException(task, "Failed to execute task because of: " + cause.getMessage(), cause);
        }
    }

    protected abstract O doExecuteTask(T task) throws Exception;

}
