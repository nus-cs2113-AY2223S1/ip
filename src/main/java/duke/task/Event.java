package duke.task;

/**
 * event class which inherits from Task
 * event needs to have a date
 */
public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String taskToString() {
        return "E" + super.taskToString() + "|" + at;
    }
}
