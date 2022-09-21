package duke.data.task;

public class Task {
    private String title;
    private Boolean isDone;
    private static final String DONE = "X";
    private static final String NOT_DONE= " ";
    private static final String STATUS_ICON_PREFIX = "[";
    private static final String STATUS_ICON_POSTFIX = "] ";

    public Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public void ConvertToDatetime(String dueBy) {}

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
        return STATUS_ICON_PREFIX + getStatusIcon() + STATUS_ICON_POSTFIX + title;
    }


    public void setStatus(Boolean isDone) {
        this.isDone = isDone;
    }
}
