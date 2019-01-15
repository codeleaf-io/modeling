package io.codeleaf.modeling.task;

public class TaskExecutionException extends Exception {

    private final Task task;

    public TaskExecutionException(Task task, String message) {
        super(message);
        this.task = task;
    }

    public TaskExecutionException(Task task, String message, Throwable cause) {
        super(message, cause);
        this.task = task;
    }

    public TaskExecutionException(Task task, Throwable cause) {
        super(cause);
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

}
