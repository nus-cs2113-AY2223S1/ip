package duke.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    private static final String DONE_ICON = "X";
    private static final String NOT_DONE_ICON = " ";

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? DONE_ICON : NOT_DONE_ICON;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

}
