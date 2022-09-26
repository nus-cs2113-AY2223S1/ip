package duke.task;

/**
 * <code>Task</code> is the super class for the Todo, Deadline and Event tasks.
 */
public class Task {

    public static final String SPACED_SEPARATOR = " | ";
    // The keyword of the command. E.g. todo, deadline, event
    private String keyword;
    // The description of the Task object.
    private String description;
    // The completion status of a task.
    private boolean isDone;

    /**
     * Constructor of <code>Task</code>.
     * Stores the keyword, description and completion status of a task.
     * Sets the initial completion status of a task to be incomplete.
     *
     * @param keyword a string that shows what type of task the task is
     * @param description the description of the task
     */
    public Task(String keyword, String description) {
        this.keyword = keyword;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the cmpletion status of a task based on the boolean isDone.
     * "X" shows that the task is completed.
     * " " shows that the task is incomplete.
     *
     * @return a string that shows the completion status of a task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * This method sets the completion status of the task to whatever the user wants.
     *
     * @param b the new boolean value, true or false, of the task
     */
    public void setDone(boolean b) {
        this.isDone = b;
    }

    /**
     * Returns the completion status of a task.
     *
     * @return the completion status of the task
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns in lowercase the keyword of a task. For example: todo, deadline or event.
     *
     * @return the keyword of the task in lowercase
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Returns the description of a task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the formatted string containing task type and whether if the task is completed.
     *
     * @return string containing task type, task description and completion status.
     */
    public String toString() {
        return  "[" + getStatusIcon() + "] " + description;
    }

    public String saveFormat() {
        String booleanInt = isDone ? "1" : "0";
        return SPACED_SEPARATOR + booleanInt + SPACED_SEPARATOR + description;
    }
}
