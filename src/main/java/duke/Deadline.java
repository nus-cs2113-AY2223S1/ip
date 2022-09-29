package duke;

/**
 * Subclass of the Task class containing methods that specifically handle the Deadline task.
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns a formatted String to be printed and displayed to the user.
     *
     * @return String formatted to specify that the task is a Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }

    /**
     * Returns a formatted String to be stored into the data file.
     *
     * @return String formatted to specify that the task is a Deadline task.
     */
    @Override
    public String convertToFileFormat() {
        String isDoneValue = (this.isDone) ? "1" : "0";
        String taskDetails = ("D|" + isDoneValue + "|" + this.task + "|" + this.by +"\n");
        return taskDetails;
    }
}
