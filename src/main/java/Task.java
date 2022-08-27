public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setStringState(Boolean state){
        this.isDone = state;
    }

    public String getDescription() {
        return this.description; // mark done task with X
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
}
