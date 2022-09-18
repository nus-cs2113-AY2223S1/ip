package duke.data.task;

public class Event extends Task {
    public static final String TYPE_EVENT = "E";
    public static final String TYPE_EVENT_WRAP = "[E]";
    public String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
        this.taskTypeWrap = TYPE_EVENT_WRAP;
        this.taskType = TYPE_EVENT;
    }

    public Event(boolean status, String description, String date) {
        this(description, date);
        this.isDone = status;
    }

    @Override
    public String toString() {
        return (this.taskTypeWrap + this.getStatusIcon() + " " + this.description + "(" + this.date + ")");
    }

    @Override
    public String toSave() {
        return (this.taskType + LIMITER + this.isDone + LIMITER + this.description + LIMITER + this.date + "\n");
    }
}
