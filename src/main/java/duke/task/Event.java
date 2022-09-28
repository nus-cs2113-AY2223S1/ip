package duke.task;

public class Event extends Task {

    String at;

    /**
     * Initializes aa Event class
     *
     * @param description What the event is about
     * @param at          Time or Date the event is stated to start
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Concatenates the task type, isDone and description value and the time of event
     *
     * @return a String with the task type isDone value enclosed by two square brackets followed by its description
     *         and the time of the event enclosed in brackets
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }

    /**
     * Similar to toString however close brackets are not included to make it easier to read
     *
     * @return a String with the task type isDone value enclosed by two square brackets followed by its description
     *         and the time of the event
     */
    public String toSaveString() {
        return "[E]" + super.toString() + "/at " + at;
    }

    /**
     * @return type of task
     */
    public String getType() {
        return "E";
    }
}