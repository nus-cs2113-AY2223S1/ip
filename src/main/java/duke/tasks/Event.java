package duke.tasks;

/**
 * This class is a specific type of {@code Task} that allows users to store events that are happening at a specific time
 *
 * @author Dhanish
 */
public class Event extends Task {

    /**
     * @return the time the {@code Event} is scheduled at.
     */
    public String getTime() {
        return at;
    }

    private final String at;

    public static final String TASK_TYPE = "E";

    /**
     * Constructor that initialises the {@code Event} based on its description and the time it is happening
     *
     * @param description - a literal that describes the {@code Event}
     * @param at          - the time the {@code Event} is scheduled to take place
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * @return - a textual representation of the {@code Event}
     */
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * @return - a {@code String} constant that denotes the type of {@code Task} this {@code Event} is.
     */
    public String getTaskType() {
        return TASK_TYPE;
    }
}
