package duke.task;

public class Event extends Task {

    protected String at;
    /**
     * Constructor of Event Task
     *
     * @param description Description of Task
     * @param at At attribute of Task
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.category = "E";
    }

    /**
     * Printed output of Event
     *
     * @return Output when Event is printed
     */
    @Override
    public String toString() {
        return "[E]" + super.toString().substring(3) + " (at: " + at + ")";
    }

    /**
     * Saved message to Storage of Event
     *
     * @return Message when Event is saved
     */
    @Override
    public String getSavedString() {
        return "D | " + getStatusIcon() + " | " + description + " | " + at;
    }
}
