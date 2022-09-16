package duke.taskings;

public class Event extends Task {

    protected String dateTime;
    protected boolean isDone;

    public Event(String taskType, String description, boolean isDone, String dateTime) {
        super(taskType, description, isDone);
        this.dateTime = dateTime;
    }

    @Override
    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "Added new event : [E] " + super.toString() + " (at: " + dateTime + ")";
    }

    @Override
    public String printList() {
        return "[E] [" + super.getStatusIcon() + "] " + super.description + " (at: " + dateTime + ")";
    }
}
