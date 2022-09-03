public abstract class Task {
    protected String description;
    protected boolean isDone;

    private static int taskCount = 0;

    public Task(String description) {
        taskCount += 1;

        setDescription(description);
        setUndone();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    // mark done task with X
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public static int getTaskCount() {
        return taskCount;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
