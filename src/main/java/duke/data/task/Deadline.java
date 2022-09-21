package duke.data.task;

/**
 * Provides the management of a deadline task with getters and setters.
 */
public class Deadline extends Task {
    private static final String TASK_TYPE = "[D]";
    private static final String TIME_PREFIX = " (by: ";
    private static final String TIME_POSTFIX = ")";
    private String dueBy;

    /**
     * Instantiates a new deadline task when user initialises a new instance of this class.
     *
     * @param title A string that represents the title of the task.
     * @param dueBy A string that represents the deadline of the task.
     * @param isDone A boolean that indicates whether the task is done or undone.
     */
    public Deadline(String title, String dueBy, boolean isDone) {
        super(title, isDone);
        this.dueBy = dueBy;
    }

    /**
     * Gets the deadline of a deadline task.
     *
     * @return A string that represents the deadline of the task.
     */
    public String getDueBy() {
        return dueBy;
    }

    /**
     * Gets the completion status, title and deadline of a deadline task.
     *
     * @return A string containing the information for a deadline task.
     */
    @Override
    public String getTaskDetails() {
        return TASK_TYPE + super.getTaskDetails() + TIME_PREFIX + getDueBy() + TIME_POSTFIX;
    }
}
