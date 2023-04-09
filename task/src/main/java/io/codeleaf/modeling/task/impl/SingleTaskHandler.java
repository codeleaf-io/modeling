package io.codeleaf.modeling.task.impl;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.task.Task;
import io.codeleaf.modeling.task.TaskHandler;
import io.codeleaf.modeling.task.TaskHandlingException;
import io.codeleaf.modeling.task.UnsupportedTaskException;

import java.util.Objects;

public abstract class SingleTaskHandler<T extends Task<O>, O> implements TaskHandler {

    private final Class<T> taskClass;

    public SingleTaskHandler(Class<T> taskClass) {
        this.taskClass = taskClass;
    }

    public Class<T> getSupportedTaskClass() {
        return taskClass;
    }

    @Override
    public <TL extends Task<?>> boolean supportsTaskType(Class<TL> taskTypeClass) {
        return Objects.equals(taskClass, taskTypeClass);
    }

    @Override
    public <OL> OL handleTask(Task<OL> task) throws TaskHandlingException {
        Objects.requireNonNull(task);
        if (!supportsTaskType(Types.cast(task.getClass()))) {
            throw new UnsupportedTaskException(task, this);
        }
        try {
            return task.getOutputType().cast(doExecuteTask(Types.cast(task)));
        } catch (TaskHandlingException cause) {
            if (task.equals(cause.getTask())) {
                throw cause;
            } else {
                throw new TaskHandlingException(task, "Failed to handle task because of a child task: " + cause.getMessage(), cause);
            }
        } catch (Exception cause) {
            throw new TaskHandlingException(task, "Failed to handle task because of: " + cause.getMessage(), cause);
        }
    }

    protected abstract O doExecuteTask(T task) throws Exception;

}
