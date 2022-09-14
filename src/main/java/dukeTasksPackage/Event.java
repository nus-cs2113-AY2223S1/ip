package dukeTasksPackage;

import dukeTasksPackage.Task;

public class Event extends Task {
    protected String time;
    public Event(String description, String time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public String toString() {
        return "   [E]" + " [" + status + "] " + description + "(at: " + time + ")";
    }
}
