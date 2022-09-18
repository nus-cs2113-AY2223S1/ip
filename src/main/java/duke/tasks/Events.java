package duke.tasks;

/**
 * Represents an event task which includes a date and time for which it is happening.
 */
public class Events extends Task {
    private String happeningAt;

    public String getHappeningAt() {
        return happeningAt;
    }

    /**
     * Constructor for a new deadline task.
     *
     * @param taskName A name or description given to the task.
     * @param happeningAt A string that represents when the event is happening.
     */
    public Events(String taskName, String happeningAt) {
        super(taskName);
        this.happeningAt = happeningAt;
        this.type = "E";
    }

    /**
     * Overrides <code>toString()</code> method for purpose of the program.
     * @return A string representing the event task.
     */
    public String toString() {
        String done = this.getIsDone() ? "[X]" : "[ ]";
        return "[E]" + done + this.getTaskName() + " (at: " + happeningAt + " )";
    }
}