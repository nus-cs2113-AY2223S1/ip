package duke.task;

public class Task {
    public static final String TYPE_TASK_WRAP = "[ ]";
    public static final String LIMITER = " | ";
    public static final String PARSE_LIMITER = "\\|";
    public static int numberOfTasks = 0;
    public String description;
    public boolean isDone;
    public String taskType;
    public String taskTypeWrap = TYPE_TASK_WRAP;

    public Task(String description) {

        this.description = description;
        this.isDone = false;
        numberOfTasks++;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); //Mark done with X
    }

    public String toString() {
        return (this.taskTypeWrap + this.getStatusIcon() + " " + this.description);
    }

    public String toStringln() {
        return this.toString() + "\n";
    }

    public String toSave() {
        return (this.taskType + this.LIMITER + this.isDone + this.LIMITER + this.description + "\n");
    }
}
