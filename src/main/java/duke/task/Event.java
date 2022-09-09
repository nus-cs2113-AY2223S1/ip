package duke.task;

/**
 * Object representation of an event task
 */
public class Event extends Task {
    protected String date;

    /**
     * Public constructor
     * @param description Task name
     * @param date Date of the event
     */
    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * Formatted data of an event task
     * @return String Formatted data of an event task
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                super.getStatusIcon(),
                super.description,
                this.date
            );
    }

    @Override
    public String getTaskData() {
        return "E" + " | " + (isDone ? 1 : 0) + " | " + description + " | " + date;
    }
}
