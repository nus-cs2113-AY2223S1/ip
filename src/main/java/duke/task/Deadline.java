package duke.task;

/**
 * Represents deadline.
 */
public class Deadline extends Task{
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the information of the deadline for printing.
     *
     * @return Deadline in string format.
     */
    @Override
    public String getTaskInfo() {
        String statusIcon = isDone ? "X" : " ";
        return "[D]" + "[" + statusIcon + "] " + this.description + " (by: " + this.by + ")";
    }
    /**
     * Sets the complete status of the deadline.
     *
     * @param isDone Complete status to be updated to.
     */
    @Override
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

/**
 * Returns the information of the deadline for storing.
 *
 * @return Deadline in string format.
 */
    @Override
    public String toString () {
        int isDoneInNumber = this.isDone ? 1 : 0;
        return String.format("D | %d | %s | %s", isDoneInNumber, description, by) + System.lineSeparator();
    }
}
