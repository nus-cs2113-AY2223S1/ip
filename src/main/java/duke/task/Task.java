package duke.task;

public class Task {
    private final String description;
    private boolean isDone;

    private static int taskNumber = 0;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskNumber++;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone(boolean b) {
        this.isDone = b;
    }

    public String toString() {
        return  "[" + getStatusIcon() + "] " + description;
    }

    public static int getTaskNumber() {
        return taskNumber;
    }
}
