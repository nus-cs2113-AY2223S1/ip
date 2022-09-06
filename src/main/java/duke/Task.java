package duke;

public class Task {
    private String description;
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

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return  "[" + getStatusIcon() + "] " + description;
    }

    public static int getTaskNumber() {
        return taskNumber;
    }
}
