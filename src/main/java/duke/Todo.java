package duke;

/**
 * Represents a task that needs to be done.
 * Contains a description, and can be done or not done.
 */

public class Todo extends Task{

    protected final String SYMBOL = new String("T");

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string that symbolises the Todo task.
     *
     * @return String symbol.
     */
    public String getSYMBOL() {
        return SYMBOL;
    }

    /**
     * Returns a string to be printed containing the status and description of the task.
     *
     * @return String to be printed.
     */
    @Override
    public String print() {
        return ("[" + this.getSYMBOL() + "]" + super.print());
    }

    /**
     * Returns a string to be written to a file in a specified format.
     * Format:
     * {task type}|{status of task}|{description}
     *
     * @return Formatted string.
     */
    @Override
    public String printToFile() {
        String isDoneStr = new String((this.isDone) ? "1" : "0");
        String s = new String(SYMBOL + "|" + isDoneStr + "|" + this.description + "\n");
        return s;
    }
}