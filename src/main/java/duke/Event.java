package duke;

/**
 * Subclass of the Task class containing methods to classify a task as an Event task.
 */
public class Event extends Task {
    protected String at;

    public Event(String description,boolean isDone, String at) {
        super(description,isDone);
        this.at = at;
    }

    /**
     * Returns a formatted String to be printed and displayed to the user.
     *
     * @return String formatted to specify that the task is an Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }

    /**
     * Returns a formatted String to be stored into the data file.
     *
     * @return String formatted to specify that the task is an Event task.
     */
    @Override
    public String convertToFileFormat() {
        String isDoneValue = (this.isDone) ? "1" : "0";
        String taskDetails = ("E|" + isDoneValue + "|" + this.task + "|" + this.at +"\n");
        return taskDetails;
    }
}
