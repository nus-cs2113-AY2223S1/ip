public class Event extends Task {
    /** To check if the task is an event task */
    protected boolean isEvent;

    public Event(String description) {
        super(description);
        this.isEvent = false;
    }

    @Override
    public String getStatusOfTypeTask() {
        //mark task with event as "E"
        return (isEvent ? "E" : " ");
    }

    @Override
    public void markTypeTask() {
        isEvent = true;
    }
}
