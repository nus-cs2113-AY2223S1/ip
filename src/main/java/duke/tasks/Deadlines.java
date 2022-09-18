package duke.tasks;

/**
 * Represents a deadline task which includes a date and time for which it is due.
 */
public class Deadlines extends Task {
    private String toBeDoneBy;

    public String getToBeDoneBy() {
        return toBeDoneBy;
    }

    /**
     * Constructor for a new deadline task.
     *
     * @param taskName A name or description given to the task.
     * @param toBeDoneBy A string that represents any deadlines for the task.
     */
    public Deadlines(String taskName, String toBeDoneBy) {
        super(taskName);
        this.toBeDoneBy = toBeDoneBy;
        this.type = "D";
    }

    /**
     * Overrides <code>toString()</code> method for purpose of the program.
     * @return A string representing the deadline task.
     */
    public String toString() {
        String done = this.getIsDone() ? "[X]" : "[ ]";
        return "[D]" + done + this.getTaskName() + " (by: " + toBeDoneBy + " )";
    }
}