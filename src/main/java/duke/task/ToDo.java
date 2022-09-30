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
     * Formats data of a Deadline task to be displayed to user
     * @return String Formatted data of a ToDo task
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                super.getStatusIcon(),
                super.description);
    }

    /**
     * Formats all attributes in the task to be saved into the data file
     * @return Task data in a format parse-able by the Storage class
     */
    @Override
    public String getTaskData() {
        return "T" + " | " + (isDone ? 1 : 0) + " | " + description;
    }
}
