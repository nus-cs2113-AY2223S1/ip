package tasks;

public class Task {
    public String descriptionString;
    public boolean isDone;

    public Task(String descriptionString) {
        this.descriptionString = descriptionString;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? " [X]" : " [ ]"); // mark done task with X
    }

    public String getDescription() {
        return descriptionString;
    }

    public String markDone() {
        this.isDone = true;
        return "marked done!";
    }

    public String unmark() {
        this.isDone = false;
        return "unamarked.";
    }

    @Override
    public String toString() {
        return this.descriptionString + this.getStatusIcon();
    }

}
