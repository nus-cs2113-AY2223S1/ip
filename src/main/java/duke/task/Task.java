package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() { return (isDone ? "X" : " "); }

    public String getDescription() {
        return description;
    }

    public String toString() { return "[" + getStatusIcon() + "] " + description; }

    public void setDone() { isDone = true; }

    public void setNotDone() { isDone = false; }


}