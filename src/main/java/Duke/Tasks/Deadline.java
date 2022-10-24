package Duke.Tasks;

/**
 * Class for dealine type tasks.
 */
public class Deadline extends Tasks {

    protected String by;

    /**
     * Deadline class constructor initialises the task description and due date.
     *
     * @param description deadline task description
     * @param by          due date of task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts Deadline task to a String output.
     *
     * @return deadline task in the format of tasktype, marked or unmarked, task description and due date.
     */
    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + super.description + " (by: " + this.by + ")";
    }

    /**
     * Converts Deadline task to a format that can be stored in a text file.
     *
     * @return deadline task variables separated by a pipe symbol (|).
     */
    @Override
    public String toFile() {
        return "D|" + ((this.isDone) ? 1 : 0) + "|" + super.description + " | " + this.by + "\n";
    }


}