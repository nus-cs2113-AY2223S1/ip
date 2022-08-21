public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Add mark: " + this.description + " [X]");
    }

    public void removeMark() {
        this.isDone = false;
        System.out.println("Remove mark: " + this.description + " [ ]");
    }

}
