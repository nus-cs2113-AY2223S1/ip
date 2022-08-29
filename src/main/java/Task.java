public class Task {
    /** Description for a task */
    protected String description;
    /** Indicates if a task is done */
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns whether a task is done or not
     *
     * @return X if task is done, " " if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "["+ getStatusIcon()+ "] " + this.description;
    }

    /**
     * Marks a task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns description for a task
     *
     * @return the description for a task
     */
    public String getDescription(){
        return this.description;
    }

}
