package task;

public class Task {
    protected String description;
    public boolean isDone;
    public Task(String description) { //creating new object
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        if (isDone) {
            return "X";
        }
        return " ";
    }
    public String toString () {
        return toString();
    }
}