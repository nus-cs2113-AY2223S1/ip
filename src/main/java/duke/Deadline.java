package duke;

/**
 * Represents a task that needs to be completed by a specified time.
 * Contains a description, can be done or not done, and a time to be completed by.
 */

public class Deadline extends Task{

    protected String date;
    protected final String SYMBOL = new String("D");

    public Deadline(String description, boolean isDone, String date) {
        super(description, isDone);
        this.date = date;
    }

    /**
     * Returns a string that symbolises the Deadline task.
     *
     * @return String symbol.
     */
    public String getSYMBOL() {
        return SYMBOL;
    }

    /**
     * Returns a string to be printed containing the status, description and deadline of the task.
     *
     * @return String to be printed.
     */
    @Override
    public String print() {
        return ("[" + this.getSYMBOL() + "]" + super.print() + " (by: " + this.date + ")");
    }

    /**
     * Returns a string to be written to a file in a specified format.
     * Format:
     * {task type}|{status of task}|{description}|{time}
     *
     * @return Formatted string.
     */
    @Override
    public String printToFile() {
        String isDoneStr = new String((this.isDone) ? "1" : "0");
        String string = new String(SYMBOL + "|" + isDoneStr + "|" + this.description + "|" + this.date + "\n");
        return string;
    }

    /**
     * Returns the date which the task must be done by.
     *
     * @return Deadline of task.
     */
    public String getDate() {
        return this.date;
    }
}