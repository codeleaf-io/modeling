package io.codeleaf.modeling.task;

public final class UnsupportedTaskException extends TaskExecutionException {

    private final TaskExecutor taskExecutor;

    public UnsupportedTaskException(Task task, TaskExecutor taskExecutor) {
        super(task, getMessage(task));
        this.taskExecutor = taskExecutor;
    }

    private static String getMessage(Task task) {
        return task == null ? "No null task allowed!" : "Task is not supported: " + task.getClass();
    }

    public TaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

}
