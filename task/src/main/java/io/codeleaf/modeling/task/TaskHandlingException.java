package io.codeleaf.modeling.task;

public class TaskHandlingException extends Exception {

    private final Task<?> task;

    public TaskHandlingException(Task<?> task, String message) {
        super(message);
        this.task = task;
    }

    public TaskHandlingException(Task<?> task, String message, Throwable cause) {
        super(message, cause);
        this.task = task;
    }

    public TaskHandlingException(Task<?> task, Throwable cause) {
        super(cause);
        this.task = task;
    }

    public Task<?> getTask() {
        return task;
    }

}
