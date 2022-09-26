package duke.task;

public class Event extends Task {

    private final String at;

    public Event(String description, String at) {
        super("event", description);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}