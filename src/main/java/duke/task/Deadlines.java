package duke.task;


public class Deadlines extends Task {
    protected String by;

    /**
     * Initializes a Deadline class
     *
     * @param description is the task description
     * @param by is the deadline date
     */
    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Concatenates the task type, isDone, description and date
     *
     * @return String
     */
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }


    public String SaveAsString() {
        return "[D]" + super.SaveAsString() + "/by " + by;
    }
}