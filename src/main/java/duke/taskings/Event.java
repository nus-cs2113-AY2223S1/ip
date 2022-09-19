package duke.taskings;

public class Event extends Task {

    protected String dateTime;
    protected boolean isDone;

    public Event(String description, String dateTime, boolean isDone) {
        super(description, isDone);
        this.dateTime = dateTime;
        this.taskType = "E";
    }

    @Override
    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + dateTime + ")";
    }

}
