package duke.task;

/**
 * Represents a event that contains a description of the task, the date of the event,
 * and a boolean attribute to determine whether the task is done or not done.
 */
public class Event extends Task {
    public String at;

    /**
     * Initializes the Event object.
     *
     * @param description Name of the task.
     * @param at Date of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Displays the event message.
     *
     * @return "[E][X/ ]Task Description (at: date)"
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
