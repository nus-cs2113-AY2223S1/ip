package duke.data.task;

/**
 * Provides the management of an event task with getters and setters.
 */
public class Event extends Task {
    private static final String TASK_TYPE = "[E]";
    private static final String TIME_PREFIX = " (at: ";
    private static final String TIME_POSTFIX = ")";
    private String time;

    /**
     * Instantiates a new event task when user initialises a new instance of this class.
     *
     * @param title A string that represents the title of the task.
     * @param time A string that represents the time that the task will be held at.
     * @param isDone A boolean that indicates whether the task is done or undone.
     */
    public Event(String title, String time, boolean isDone) {
        super(title, isDone);
        this.time = time;
    }

    /**
     * Gets the time of an event task.
     *
     * @return A string that represents the time that the task will be held at.
     */
    public String getTime() {
        return time;
    }

    /**
     * Gets the completion status, title and event time of an event task.
     *
     * @return A string containing the information for an event task.
     */
    @Override
    public String getTaskDetails() {
        return TASK_TYPE + super.getTaskDetails() + TIME_PREFIX + getTime() + TIME_POSTFIX;
    }
}
