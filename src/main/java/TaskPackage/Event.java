package TaskPackage;

import TaskPackage.Task;

/*
Represents Event, a type of Task
 */
public class Event extends Task {
    protected String at;
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}