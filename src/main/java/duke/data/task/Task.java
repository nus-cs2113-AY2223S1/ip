package duke.data.task;

/**
 * <code>Task</code> is the abstract base class for every type of tasks in the application.
 */
public class Task {
    // The description of the task.
    private String taskName;
    // The completion status of the task.
    private boolean isDone;

    /**
     * Constructor of <code>Task</code>. The isDone boolean is set to false by default.
     *
     * @param taskName Description of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Get the task description.
     *
     * @return
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Get the completion status of task.
     *
     * @return A boolean value to represent whether a task is completed.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Set the completion status of a task.
     *
     * @param done A boolean value to represent whether a task is completed.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Return the completion status of the task as an icon.
     * "X" represents completed/done.
     * " " represents not completed/not done.
     *
     * @return A string icon of the task's completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Return the formatted task details with task name and task completion status icon.
     *
     * @return A string containing the formatted task details.
     */
    public String getTaskFullDetails() {
        return String.format("[%s] %s", getStatusIcon(), getTaskName());
    }
}
