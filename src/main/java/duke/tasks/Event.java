package duke.tasks;

public class Event extends Task {

    public String getTime() {
        return at;
    }

    private String at;

    public static final String TASK_TYPE = "E";

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String toString() {
        return "[" + getTaskType() + "]" + super.toString() + " (at: " + at + ")";
    }

    public String getTaskType() {
        return TASK_TYPE;
    }
}
