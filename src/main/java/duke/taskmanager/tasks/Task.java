package duke.taskmanager.tasks;

/**
 * Represents the simplest task
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected abstract String  getTypeIcon();

    public String getStatusIcon() {
        return (this.isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    @Override
    public String toString() {
        return this.getTypeIcon() + this.getStatusIcon()
                + this.getDescription();
    }

    /**
     * Mark current task done or not done
     *
     * @param done whether task is done
     * @return output message to be formatted
     */
    public String markDone(boolean done) {
        setDone(done);
        StringBuilder markMessage = new StringBuilder();
        if (done) {
            markMessage.append("Nice! I've marked this task as done:");
        } else {
            markMessage.append("OK, I've marked this task as not done yet:");
        }
        markMessage.append(System.lineSeparator()).append(this);
        return String.valueOf(markMessage);
    }
}
