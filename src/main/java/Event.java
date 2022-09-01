public class Event extends Task {
    protected boolean isEventCheck;

    public Event(String description) {
        super(description);
        this.isEventCheck = false;
    }

    @Override
    public String getStatusOfTypeTask() {
        //mark task with event as "E"
        return (isEventCheck ? "E" : " ");
    }

    @Override
    public void markTypeTask() {
        isEventCheck = true;
    }
}
