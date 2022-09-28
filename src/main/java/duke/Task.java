package duke;

/**
 * Represents a task.
 * Contains a description, and can be done or not done.
 */

public class Task {
    public String getSYMBOL;
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a string that symbolises completed tasks.
     *
     * @return String symbol.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the status of the task.
     *
     * @return Status of task.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the status of this task to done.
     *
     * @param done True for done. False for not done.
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Returns a string to be printed containing the status and description of the task.
     *
     * @return String to be printed.
     */
    public String print() {
        String s = new String("[" + this.getStatusIcon() + "] " + this.getDescription());
        return s;
    }

    /**
     * Returns a string to be written to a file in a specified format.
     * Format:
     * {task type}|{status of task}|{description}
     *
     * @return Formatted string.
     */
    public String printToFile() {
        String isDoneStr = new String((this.isDone) ? "1" : "0");
        String s = new String("T|" + isDoneStr + "|" + this.description + "\n");
        return s;
    }

}