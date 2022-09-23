package duke.task;

/**
 * Represents the task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract String getTaskInfo();

    public abstract void setDone(boolean isDone);

}
