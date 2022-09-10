package duke.task;

import duke.task.Task;

public class Event extends Task {
    private String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    public String getEventTime() {
        return eventTime;
    }

    @Override
    public String getTaskDetails() {
        return "[E]" + super.getTaskDetails() + " (at: " + eventTime + ")";
    }
}
