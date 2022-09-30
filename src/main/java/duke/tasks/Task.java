package duke.tasks;

/**
 * Represents a task which might be todos, deadline, or event.
 */
public abstract class Task {
    private String taskName;
    private boolean isDone;
    protected String type;

    public String getType() {
        return type;
    }

    /**
     * Constructor for a new task.
     *
     * @param taskName A name or description given to the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void setIsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void setIsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the name of the task.
     *
     * @return Name of the task.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns a boolean for whether the task is marked as done.
     *
     * @return Boolean for if the task is done.
     */
    public boolean getIsDone() {
        return isDone;
    }
}