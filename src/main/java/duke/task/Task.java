package duke.task;

/**
 * An abstract class representing a task. It has 2 class attributes, including description that describe what the task
 * is for, and a boolean variable <code>isDone</code> which states whether the task has been completed already.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    private static final String DONE_VALUE = "1";
    private static final String UNDONE_VALUE = "0";

    private static final String DONE_ICON = "X";
    private static final String UNDONE_ICON = " ";

    /**
     * Constructor of the <code>Task</code> class specifying its description.
     * @param description Description of the task to be created.
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Construct the <code>Task</code> object from the given description and status.
     * @param description Description of the task to be created.
     * @param taskStatusString Status of whether the task is done or not.
     */
    protected Task(String description, String taskStatusString) {
        this.description = description;
        this.isDone = getTaskStatus(taskStatusString);
    }

    /**
     * Return the boolean value of whether the task status string specified by the taskStatusString is
     * <code>DONE_VALUE</code> or <code>UNDONE_VALUE</code>.
     * It returns true if the task status string is <code>DONE_VALUE</code>, otherwise false.
     * @param taskStatusString The string representing the task status in the data file.
     *                         It is either <code>DONE_VALUE</code> or <code>UNDONE_VALUE</code>.
     * @return Boolean value of whether the task is done or not. If the task is done, it returns true, otherwise false.
     */
    private boolean getTaskStatus(String taskStatusString) {
        return taskStatusString.equals(DONE_VALUE);
    }

    /**
     * Return the icon of the status to be shown on the user interface when the task is being listed.
     * If the task is done, it returns <code>DONE_ICON</code>. If not, then it returns <code>UNDONE_ICON</code>.
     * @return Icon of the status in whether the task is done or not.
     */
    private String getStatusIcon() {
        return (isDone ? DONE_ICON : UNDONE_ICON);
    }

    /**
     * Return the string of the status to be shown on the user interface when the task is being listed.
     * If the task is done, it returns <code>DONE_VALUE</code>. If not, then it returns <code>UNDONE_VALUE</code>.
     * @return String of the status in whether the task is done or not.
     */
    protected String getStatusValue() {
        return (isDone ? DONE_VALUE : UNDONE_VALUE);
    }

    /**
     * Mark the task's status as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Mark the task's status as not done.
     */
    public void unmarkDone() {
        isDone = false;
    }

    /**
     * Return the formatted string a <code>Task</code> object is represented by.
     * @return The formatted string a <code>Task</code> object is displayed.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), description);
    }

    /**
     * Return the string that represent the <code>Task</code> object in the data file.
     * The string is corresponding to a line in the file.
     * @return The formatted string that the <code>Task</code> object is represented in the data file.
     */
    public abstract String getStringForSave();
}
