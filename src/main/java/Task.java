public class Task {
    protected String description;
    protected boolean isDone;

    protected int id;
    public Task(String description, int id) {
        this.description = description;
        this.isDone = false;
        this.id = id;
    }

    public String getStatusIcon() {
        if (isDone) { // mark done task with X
            return "[X] ";
        } else {
            return "[ ] ";
        }
    }
}