package duke.data.task;

/** Represent Event Task */
public class Event extends Task {
    public static final String TYPE_EVENT = "E";

    public Event(String description, String date) {
        super(description);
        this.date = new Date(date);
        this.taskType = TYPE_EVENT;
    }
    
    /** Initiate with isDone */
    public Event(boolean status, String description, String date) {
        this(description, date);
        this.isDone = status;
    }

    @Override
    public String toString() {
        return (this.wrapType(taskType) + this.getStatusIcon() + " " + this.description.getData() + "\t(" + this.date.getData()
                + ")");
    }
}
