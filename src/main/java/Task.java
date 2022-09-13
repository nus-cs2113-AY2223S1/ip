public abstract class Task {
    /** Description for a task */
    protected String description;
    /** Indicates if a task is done */
    protected boolean isDone;

    public Task(String description) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException();
        }
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

    /**
     * Returns formatted string
     *
     * @return string to print
     */
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

    public String toSaveString() {
        return (isDone ? "1" : "0") + "|" + description;

    }
}
