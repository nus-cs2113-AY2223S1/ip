package duke.task;

/**
 * <code>Event</code> is a task with a starting time which is represented by the string at.
 */
public class Event extends Task {

    // the starting time of the task
    private final String at;

    /**
     * Constructor of the <code>Event</code> object.
     * Stores the starting time of the task in this class and the description in the Task class.
     *
     * @param description description field of the event object
     * @param at starting time of the event object
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the time an event starts
     *
     * @return the time the event object is starting
     */
    public String getAt() {
        return at;
    }

    /**
     * Returns the formatted string containing task type and completion status.
     *
     * @return string containing task type, task description and completion status.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns the formatted string containing task type, completion status indicated by 1 or 0,
     * task description and the starting time of the task.
     *
     * @return the formatted string with task details
     */
    @Override
    public String saveFormat() {
        return "E" + super.saveFormat() + SPACED_SEPARATOR + at;
    }
}