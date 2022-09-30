package duke.tasks;


/**
 * Represents the task of type Deadline
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Sets the time of the deadline
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Gets the time of the deadline
     *
     * @return the time of the deadline.
     */
    public String getBy() {
        return by;
    }


    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

}
