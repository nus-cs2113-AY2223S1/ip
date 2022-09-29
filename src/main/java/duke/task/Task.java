package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    private static int taskCount = 0;

    /**
     * Represents a new Task. A <code>Task</code> object corresponds to a description of the task
     * and the status of the task. The task is set to be undone at initialisation.
     * 
     * @param description the description of the new task
     */
    public Task(String description) {
        taskCount += 1;

        setDescription(description);
        setUndone();
    }

    /**
     * Returns the description of the task
     * 
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task
     * 
     * @param description the task description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set the state of the task to be done
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Set the state of the task to be undone
     */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Get the state of the task depending on whether it is done or undone
     * 
     * @return the state of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Get the current number of tasks
     * 
     * @return the current number of tasks
     */
    public static int getTaskCount() {
        return taskCount;
    }

    /**
     * Formats how to display the state of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Update the number of tasks when one task is deleted
     */
    public static void deleteTask() {
        taskCount -= 1;
    }
}
