public abstract class Task {
    /** Description for a task */
    protected String description;
    /** Indicates if a task is done */
    protected boolean isDone;

    public Task(String description) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("☹ OOPS!!! The description of a task cannot be empty.");
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

    /**
     * Returns formatted string for saved file
     *
     * @return string to save
     */
    public String toSaveString() {
        return (isDone ? "1" : "0") + "|" + description;
    }

    /**
     * returns description
     *
     * @return description
     */
    public String getDescription() {
        return this.description;
    }
}
