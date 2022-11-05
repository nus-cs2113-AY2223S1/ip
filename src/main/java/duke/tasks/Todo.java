package duke.tasks;

/**
 * This class represents the most generic type of {@code Task} that can be instantiated, one with no times or deadlines.
 *
 * @author Dhanish
 */
public class Todo extends Task {

    public static final String TASK_TYPE = "T";

    /**
     * Constructor that initialises this {@code Todo} based off the input description
     *
     * @param description - a literal that describes the {@code Todo}
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * @return - a textual representation of what this {@code Todo} is.
     */
    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString();
    }

    /**
     * @return - a {@code String} constant that describes the type of {@code Task} this {@code Todo} is.
     */
    public String getTaskType() {
        return TASK_TYPE;
    }
}
