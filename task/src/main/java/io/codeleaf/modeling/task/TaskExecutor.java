package io.codeleaf.modeling.task;

public interface TaskExecutor {

    <T extends Task<?>> boolean supportsTaskType(Class<T> taskTypeClass);

    <O> O executeTask(Task<O> task) throws TaskExecutionException;

}
