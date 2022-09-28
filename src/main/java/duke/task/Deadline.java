package duke.task;

public class Deadline extends Task {

    String by;

    /**
     * Initializes a Deadline class
     *
     * @param description What the deadline is about
     * @param by          Time or Date the deadline is stated to be finished by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    /**
     * Concatenates the task type, isDone and description value and the time of deadline
     *
     * @return a String with the task type isDone value enclosed by two square brackets followed by its description
     *         and the time of the deadline enclosed in brackets
     */
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    /**
     * Similar to toString however close brackets are not included to make it easier to read
     *
     * @return a String with the task type isDone value enclosed by two square brackets followed by its description
     *         and the time of the deadline
     */
    public String toSaveString() {
        return "[D]" + super.toString() + "/by " + by;
    }

    /**
     * @return type of task
     */
    public String getType() {
        return "D";
    }
}