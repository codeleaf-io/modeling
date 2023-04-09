package io.codeleaf.modeling.task;

public interface TaskHandler {

    <TL extends Task<?>> boolean supportsTaskType(Class<TL> taskTypeClass);

    <OL> OL handleTask(Task<OL> task) throws TaskHandlingException;

    default <OL> TaskCommand<OL> createCommand(Task<OL> task) {
        return TaskCommand.create(this, task);
    }
}
