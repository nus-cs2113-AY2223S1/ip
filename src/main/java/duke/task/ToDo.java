package duke.task;

/**
 * Representation of ToDo task
 */
public class ToDo extends Task {

    /**
     * Public constructor
     * @param description Task name
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Provides formatted data of a ToDo task
     * @return String Formatted data of a ToDo task
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                super.getStatusIcon(),
                super.description);
    }

}
