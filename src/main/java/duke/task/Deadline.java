package duke.task;

/**
 * Object representation of Deadline task
 */
public class Deadline extends Task {
    protected String date;
    protected String time;

    /**
     * Public constructor
     * @param description Task name
     * @param date Date of deadline
     * @param time Time of deadline
     */
    public Deadline(String description, String date, String time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Formatted data of a Deadline task
     * @return String Formatted data of a deadline task
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s %s)",
                super.getStatusIcon(),
                super.description,
                this.date,
                this.time
        );
    }

    @Override
    public String getTaskData() {
        return "D" + " | " + (isDone ? 1 : 0) + " | " + description + " | " + date + " | " + time;
    }
}
