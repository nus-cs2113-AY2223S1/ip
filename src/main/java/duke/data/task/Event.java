package duke.data.task;

public class Event extends Task {
    private String eventTime;

    public Event(String taskName, String eventTime) {
        super(taskName);
        this.eventTime = eventTime;
    }

    public String getEventTime() {
        return eventTime;
    }

    @Override
    public String getTaskFullDetails() {
        return String.format("[E]%s (at: %s)",
                super.getTaskFullDetails(), this.getEventTime());
    }
}
