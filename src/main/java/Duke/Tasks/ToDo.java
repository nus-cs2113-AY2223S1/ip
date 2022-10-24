package Duke.Tasks;

/**
 * Class for todo type tasks.
 */
public class ToDo extends Tasks {

    /**
     * Todo class constructor initialises the description of the todo task.
     *
     * @param description todo task description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts Todo task to a String output.
     *
     * @return todo task in the format of tasktype, marked or unmarked and task description.
     */
    @Override
    public String toString() {

        return "[T]" + getStatusIcon() + super.description;
    }

    /**
     * Converts Todo task to a format that can be stored in a text file.
     *
     * @return Todo task variables separated by a pipe symbol (|).
     */
    @Override
    public String toFile() {
        return "T|" + ((this.isDone) ? "1" : "0") + "|" + super.description + "\n";
    }
}
