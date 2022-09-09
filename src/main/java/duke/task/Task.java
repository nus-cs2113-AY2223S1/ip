package duke.task;

public class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark task done with X
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + description;
    }

    public void markTaskAsDone() {
        isDone = true;
    }

    public void markTaskAsUndone() {
        isDone = false;
    }

    public String formattedInformation() { //dummy function for overriding by subclasses
        return "";
    }
}