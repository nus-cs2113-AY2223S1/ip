package duke.task;

public class Task {
    public String taskName;
    public int taskNum;
    public boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getStatus() {
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    public String toString() {
        return "[" + getStatus() + "] " + taskName;
    }
}