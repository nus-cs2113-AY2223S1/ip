package duke.task;
public class Event extends Task {
    protected String at;

    /**
     * Creates a new event object
     * 
     * @param description the description of the event
     * @param at the time of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Formats how to print the event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
