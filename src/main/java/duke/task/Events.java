package duke.task;


public class Events extends Task {
    protected String at;

    /**
     * Initializes Event class
     *
     * @param description refers to event description
     * @param at refers to the event date
     */
    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }

    public String SaveAsString() {
        return "[E]" + super.SaveAsString() + "/at " + at;
    }
}
