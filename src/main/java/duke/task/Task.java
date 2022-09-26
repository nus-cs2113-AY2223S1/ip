package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    private static final String DONE_VALUE = "1";
    private static final String UNDONE_VALUE = "0";

    private static final String DONE_ICON = "X";
    private static final String UNDONE_ICON = " ";

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, String taskStatusString) {
        this.description = description;
        this.isDone = getTaskStatus(taskStatusString);
    }

    public boolean getTaskStatus(String taskStatusString) {
        return taskStatusString.equals(DONE_VALUE);
    }

    public String getStatusIcon() {
        return (isDone ? DONE_ICON : UNDONE_ICON);
    }

    public String getStatusValue() {
        return (isDone ? DONE_VALUE : UNDONE_VALUE);
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), description);
    }

    public abstract String getStringForSave();
}
