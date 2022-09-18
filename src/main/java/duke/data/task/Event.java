package duke.data.task;

/**
 * <code>Event</code> represents a task to be completed by a user at a specific date and time.
 */
public class Event extends Task {
    // The date and time when the task should be done.
    private String eventTime;

    /**
     * Constructor of <code>Event</code>. Stores the description and date time of the task.
     *
     * @param taskName  Description of the task.
     * @param eventTime Time when the task should be completed.
     */
    public Event(String taskName, String eventTime) {
        super(taskName);
        this.eventTime = eventTime;
    }

    /**
     * Return the time when the event should be completed.
     *
     * @return A date time value of the task.
     */
    public String getEventTime() {
        return eventTime;
    }

    /**
     * Return the formatted event task details with task name, task completion status icon and date time.
     *
     * @return A string containing the formatted event task details.
     */
    @Override
    public String getTaskFullDetails() {
        return String.format("[E]%s (at: %s)",
                super.getTaskFullDetails(), this.getEventTime());
    }
}
