package duke.data.task;

/**
 * <code>Deadline</code> represents a task to be completed by a user before a specific date and time.
 */
public class Deadline extends Task {
    // The date and time before the task is to be done.
    private String deadlineTime;

    /**
     * Constructor of <code>Event</code>. Stores the description and date time of the task.
     *
     * @param taskName     Description of the task.
     * @param deadlineTime Time before the task is to be completed.
     */
    public Deadline(String taskName, String deadlineTime) {
        super(taskName);
        this.deadlineTime = deadlineTime;
    }

    /**
     * Return the time before the deadline is to be completed.
     *
     * @return A date time value of the task.
     */
    public String getDeadlineTime() {
        return deadlineTime;
    }

    /**
     * Return the formatted deadline task details with task name, task completion status icon and date time.
     *
     * @return A string containing the formatted deadline task details.
     */
    @Override
    public String getTaskFullDetails() {
        return String.format("[D]%s (by: %s)",
                super.getTaskFullDetails(), this.getDeadlineTime());
    }
}
