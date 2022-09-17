package duke.data.task;

public class Deadline extends Task {
    public static final String TYPE_DEADLINE = "D";
    public static final String TYPE_DEADLINE_WRAP = "[D]";
    public String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
        this.taskTypeWrap = TYPE_DEADLINE_WRAP;
        this.taskType = TYPE_DEADLINE;
    }

    public Deadline(boolean status, String description, String date) {
        this(description, date);
        this.isDone = status;
    }

    @Override
    public String toString() {
        return (this.taskTypeWrap + this.getStatusIcon() + " " + this.description + "\t(" + this.date + ")");
    }

    @Override
    public String toSave() {
        return (this.taskType + LIMITER + this.isDone + LIMITER + this.description + LIMITER + this.date + "\n");
    }

}
