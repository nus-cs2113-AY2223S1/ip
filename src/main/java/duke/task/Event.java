package duke.task;

/**
 * Object representation of an event task
 */
public class Event extends Task {
    protected String date;
    protected String time;


    /**
     * Public constructor
     * @param description Task name
     * @param date Date of the event
     * @param time Time of event
     */
    public Event(String description, String date, String time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Formats data of an Event task to be displayed to user
     * @return String Formatted data of an event task
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s %s)",
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
        return "E" + " | " + (isDone ? 1 : 0) + " | " + description + " | " + date + " | " + time;
    }
}
