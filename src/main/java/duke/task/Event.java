package duke.task;

public class Event extends Task {
    /** To check if the task is an event task */
    protected boolean isEvent;

    public Event(String description) {
        super(description);
        this.isEvent = false;
    }

    @Override
    public String getStatusOfTypeTask() {
        String status = " ";
        if (isEvent) {
            status = "E";
        }
        return status;
    }

    @Override
    public void markTypeTask() {
        isEvent = true;
    }
}
