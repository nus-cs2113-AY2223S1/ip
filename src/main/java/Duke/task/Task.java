package Duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done Duke.task with X
    }

    public void setDone() {
        isDone = true;
    }

    public void setUnDone() {
        isDone = false;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.description;
    }
}