package io.codeleaf.modeling.task;

public interface TaskHandler {

    <T extends Task<?>> boolean supportsTaskType(Class<T> taskTypeClass);

    <O> O handleTask(Task<O> task) throws TaskHandlingException;

    default <O> TaskCommand<O> createCommand(Task<O> task) {
        return TaskCommand.create(this, task);
    }
}
