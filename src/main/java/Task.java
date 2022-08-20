public class Task {
    protected String description;
    protected boolean isDone;
    protected static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        Task.taskCount++;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public String getDescription() {
        return description;
    }
}
