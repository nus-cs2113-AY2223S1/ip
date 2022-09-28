package duke;

public class Task {
    protected String task;
    protected boolean isDone;


    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    public String getTask() {
        return task;
    }

    public void markAsDone() {

        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String toString() {
        return "[" + getStatusIcon() + "]" + task;
    }

    public String convertToFileFormat() {
        String isDoneValue = (this.isDone) ? "1" : "0";
        return isDoneValue;
    }
}
