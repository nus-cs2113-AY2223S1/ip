package duke.tasks;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getTaskType() {
        return "[E]";
    }

    @Override
    public String getAddedInfo() {
        return " (at: " + at + ")";
    }
}
