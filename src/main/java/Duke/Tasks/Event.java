package Duke.Tasks;

/**
 * Class for event type tasks.
 */
public class Event extends Tasks {
    protected String at;

    /**
     * Event class constructor initialises the task description and event details.
     *
     * @param description event task description
     * @param at          event details for example: location, time or date of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Converts Event task to a String output.
     *
     * @return event task in the format of tasktype, marked or unmarked, task description and event details.
     */
    @Override
    public String toString() {

        return "[E]" + getStatusIcon() + super.description + " (at: " + this.at + ")";
    }

    /**
     * Converts Event task to a format that can be stored in a text file.
     *
     * @return event task variables separated by a pipe symbol (|).
     */
    @Override
    public String toFile() {
        return "E|" + ((this.isDone) ? 1 : 0) + "|" + super.description + " | " + this.at + "\n";
    }
}
