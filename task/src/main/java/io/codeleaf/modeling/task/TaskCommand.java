package io.codeleaf.modeling.task;

import java.util.Objects;

public final class TaskCommand<O> {

    private final TaskHandler taskHandler;
    private final Task<O> task;

    private TaskCommand(TaskHandler taskHandler, Task<O> task) {
        this.taskHandler = taskHandler;
        this.task = task;
    }

    public static <O> TaskCommand<O> create(TaskHandler taskHandler, Task<O> task) {
        Objects.requireNonNull(taskHandler);
        Objects.requireNonNull(task);
        return new TaskCommand<>(taskHandler, task);
    }

    public TaskHandler getTaskHandler() {
        return taskHandler;
    }

    public Task<O> getTask() {
        return task;
    }

    public O invoke() throws TaskHandlingException {
        return taskHandler.handleTask(task);
    }
}
