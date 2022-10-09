public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setAsDone(Boolean status) {
        isDone = status;
    }
    //@@author {brian-vb}-reused
    //{Reused from chydarren, to get the TaskDetails easily}
    public String getTaskDetails() {
        return "[" + getStatusIcon() + "] " + description;
    }
    //@@author
}