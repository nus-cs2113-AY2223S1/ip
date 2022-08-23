public class Task {
    private String taskName;
    private boolean isDone;

    public Task() {
        taskName = "";
        isDone = false;
    }

    public Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
    }

    public Task(String taskName, boolean status) {
        this.taskName = taskName;
        this.isDone = status;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
