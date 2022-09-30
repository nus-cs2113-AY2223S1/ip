package task;
/**
 * in charge of type deadline of the task
 */
public class Deadline extends Task {

    protected String by;

    /**
     * used when both description and deadline time are given
     *
     * @param description description of the task
     * @param by the deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        System.out.println("  " + this);
    }

    /**
     * used when description, deadline time, and status are given
     *
     * @param description description of the task
     * @param by the deadline of the task
     * @param status the status of the task
     */
    public Deadline(String description, String by, boolean status) {
        super(description, status);
        this.by = by;
        System.out.println("  " + this);
    }

    public String getBy(){
        return by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}