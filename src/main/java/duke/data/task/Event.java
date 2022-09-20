package duke.data.task;

public class Event extends Task {
    private static final String TASK_TYPE = "[E]";
    private static final String TIME_PREFIX = " (at: ";
    private static final String TIME_POSTFIX = ")";
    private String time;

    public Event(String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String getTaskDetails() {
        return TASK_TYPE + super.getTaskDetails() + TIME_PREFIX + getTime() + TIME_POSTFIX;
    }
}
