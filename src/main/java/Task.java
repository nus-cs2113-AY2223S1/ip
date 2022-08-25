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

    public String getDescription() {
        return description;
    }

    public void setDone(boolean done) {
        if (done == false) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }
}