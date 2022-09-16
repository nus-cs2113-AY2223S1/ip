package duke.task;

/**
 * Represents an event that the user wishes to take note of.
 */
public class Event extends Task {
    private String dayAndTime;
    public Event(String description, String dayAndTime) {
        super(description);
        this.dayAndTime = dayAndTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dayAndTime + ")";
    }

    /**
     * Processes a task's attributes to produce a string that encapsulates the task information which includes its type, done status and description.
     *
     * @return A well-formatted string containing the task information.
     */
    @Override
    public String formattedInformation() {
        String divider = " | ";
        String information = "E" + divider + (isDone ? "1" : "0") + divider + this.description + divider + this.dayAndTime;
        return information;
    }
}