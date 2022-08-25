public class Task {
    /** Represent the individual task that the user input */
    protected String description;
    /** To check if the task is mark as done or not for each individual task */
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        //mark done task with X
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }
}
