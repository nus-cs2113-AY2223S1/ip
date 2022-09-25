package duke.taskmanager.tasks;

/**
 * Represents the simplest
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

//    public Task() {
//        setDescription(null);
//        setDone(false);
//    }

    protected String getTypeIcon() {
        return "[T]";
    }

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
