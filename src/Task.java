public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public void setDone(boolean status) {
        isDone = status;
    }
    public boolean isDone() {
        return isDone;
    }
    public String getDescription() {
        return description;
    }
    public String getStatus() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    public String getDescriptionAndStatus() {
        return getStatus() + " " + getDescription();
    }
}