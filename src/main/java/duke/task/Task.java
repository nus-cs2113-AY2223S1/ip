package duke.task;

public class Task {
    private String title;
    private Boolean isDone;
    protected static final String DONE = "X";
    protected static final String NOT_DONE= " ";

    public Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public String getDueBy() {
        return getDueBy();
    }

    public String getTime() {
        return getTime();
    }

    public String getTitle() {
        return title;
    }

    public String getStatusIcon() {
        return (isDone ? DONE : NOT_DONE);
    }

    public String getTaskDetails() {
        return "[" + getStatusIcon() + "] " + title;
    }

    public void setStatus(Boolean isDone) {
        this.isDone = isDone;
    }
}
