package duke.task;

/**
 * Object representation of Deadline task
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Public constructor
     * @param description Task name
     * @param by deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Formatted data of a Deadline task
     * @return String Formatted data of a deadline task
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                super.getStatusIcon(),
                super.description,
                this.by
        );
    }

    @Override
    public String getTaskData() {
        return "D" + " | " + (isDone ? 1 : 0) + " | " + description + " | " + by;
    }
}
