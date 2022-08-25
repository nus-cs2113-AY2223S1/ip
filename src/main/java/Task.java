public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName, boolean isDone) {
        setTaskName(taskName);
        setDone(isDone);
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
