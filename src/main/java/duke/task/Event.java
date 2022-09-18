package duke.task;

/**
 * Stores events.
 */
public class Event extends Task {
    protected String at;

    /**
     * Initializes event object.
     *
     * @param description Description of the event.
     * @param at Date and time of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
}
