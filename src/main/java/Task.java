public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected String deadline;

    public Task(String type, String description) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone() {
        isDone = true;
    }

    public void setUnDone() {
        isDone = false;
    }
}