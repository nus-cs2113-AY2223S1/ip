package Duke.task;

public class Event extends Task {

    public Event(String description, String at) {
        super(description);
        this.by = at;
        this.type = "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + by + ")";
    }
}