package Duke.task;

/**
 * Event tasks are tasks that start at a specific time and ends at a specific time.
 */
public class Event extends Task {
    /**
     * Create new Event task
     * @param description Description of task
     * @param at Time of Event task
     */
    public Event(String description, String at) {
        super(description);
        this.by = at;
        this.type = "E";
    }

    /**
     * Format the task as a String to display as output
     * @return Formatted String of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + by + ")";
    }
}