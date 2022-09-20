package Duke.task;

public class Task {
    public String description;
    public boolean isDone;
    public String type;
    public String by;

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