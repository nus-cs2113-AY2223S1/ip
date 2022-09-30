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
     * Formats data of a Deadline task to be displayed to user
     * @return Formatted data of a deadline task
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

    /**
     * Formats all attributes in the task to be saved into the data file
     * @return Task data in a format parse-able by the Storage class
     */
    @Override
    public String getTaskData() {
        return "D" + " | " + (isDone ? 1 : 0) + " | " + description + " | " + date + " | " + time;
    }
}
