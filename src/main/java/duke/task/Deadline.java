package duke.task;

/**
 * <code>Deadline</code>> is a task with a due time which is represented by the string by.
 */
public class Deadline extends Task {

    public static final String SPACED_SEPARATOR = " | ";
    // the due time of the task
    private final String by;

    /**
     * Constructor of the <code>Deadline</code> object.
     * Stores the due time of the task in this class and the description in the Task class.
     *
     * @param description description field of the deadline object
     * @param by due date of the deadline object
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the due date of the deadline object.
     *
     * @return due date of the deadline object
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns the formatted string containing task type and whether if the task is completed.
     *
     * @return string containing task type, task description and completion status.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the formatted string containing task type, completion status indicated by 1 or 0,
     * task description and the due time of the task.
     *
     * @return the formatted string with task details
     */
    @Override
    public String saveFormat() {
        return "D" + super.saveFormat() + SPACED_SEPARATOR + by;
    }
}