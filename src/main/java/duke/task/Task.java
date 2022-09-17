package duke.task;

public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setAsDone() {
        this.isDone = true;
    }

    public void setAsUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String formatTaskToStringToStore() {
        String STORE_DIVIDER = " | ";
        return (isDone ? "1" : "0") + STORE_DIVIDER + description;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.description;
    }
}
