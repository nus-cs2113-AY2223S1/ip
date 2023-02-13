/**
 * Deadline class
 * extends the Task class to store a particular type of task (deadline)
 * contains a description of the task (String description)
 * contains a deadline (String by)
 */
public class Deadline extends Task{

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
