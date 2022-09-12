package duke;

public class Event extends Task {

    protected String at;
    /**
     *
     * @param description
     * @param at
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.category = "E";
    }


    @Override
    public String toString() {
        return "[E]" + super.toString().substring(3) + " (at: " + at + ")";
    }

    @Override
    public String getSavedString() {
        return "D | " + getStatusIcon() + " | " + description + " | " + at;
    }
}
