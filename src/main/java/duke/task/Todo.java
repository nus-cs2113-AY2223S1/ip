package duke.task;

/**
 * Represents a task that has to be done by the user but does not have a specific deadline nor a specific time/venue.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Processes a task's attributes to produce a string that encapsulates the task information which includes its type, done status and description.
     *
     * @return A well-formatted string containing the task information.
     */
    @Override
    public String formattedInformation() {
        String divider = " | ";
        String information = "T" + divider + (isDone ? "1" : "0") + divider + this.description;
        return information;
    }
}