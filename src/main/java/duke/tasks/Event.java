package duke.tasks;

/**
 * Represent a task with description, status, and time of occurrence specified.
 */
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
