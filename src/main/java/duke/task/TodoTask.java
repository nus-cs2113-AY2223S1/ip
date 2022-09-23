package duke.task;

import duke.DukeException;

/**
 * A TodoTask represents a simple task to be done.
 */
public class TodoTask extends Task {
    public static final String KEYWORD = "todo";
    private static final String NAME_EMPTY_ERROR_MESSAGE = "Todo name cannot be empty";

    /**
     * Creates a new TodoTask object.
     * 
     * @param name Name of the task
     * @throws DukeException Throws an exception if name is not provided
     */
    public TodoTask(String name) throws DukeException {
        this(name, false);
    }

    /**
     * Creates a new TodoTask object.
     * 
     * @param name   Name of the task
     * @param status True if the task is done and false otherwise
     * @throws DukeException Throws an exception if name is not provided
     */
    public TodoTask(String name, boolean status) throws DukeException {
        super(name, status);
        if ("".equals(name)) {
            throw new DukeException(NAME_EMPTY_ERROR_MESSAGE);
        }
    }

    /**
     * Formats a todo task as a string to be displayed.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formats a todo task to be saved to a file.
     */
    @Override
    public String serialize() {
        return super.serialize();
    }

    @Override
    public String getKeyword() {
        return KEYWORD;
    }
}
