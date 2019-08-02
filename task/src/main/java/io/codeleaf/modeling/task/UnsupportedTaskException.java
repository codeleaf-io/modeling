package io.codeleaf.modeling.task;

public final class UnsupportedTaskException extends TaskHandlingException {

    private final TaskHandler taskHandler;

    public UnsupportedTaskException(Task<?> task, TaskHandler taskHandler) {
        super(task, getMessage(task));
        this.taskHandler = taskHandler;
    }

    private static String getMessage(Task<?> task) {
        return task == null ? "No null task allowed!" : "Task is not supported: " + task.getClass();
    }

    public TaskHandler getTaskHandler() {
        return taskHandler;
    }

}
