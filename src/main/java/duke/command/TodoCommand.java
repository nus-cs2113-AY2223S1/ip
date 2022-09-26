package duke.command;

/**
 * <code>TodoCommand</code> is a class for todo commands.
 * It stores parsed parts of user input necessary for creation of todo object, namely
 * the description of task.
 */
public class TodoCommand extends Command {

    private String description;

    /**
     * Constructor of <code>TodoCommand</code>.
     */
    public TodoCommand() {
        super();
    }

    /**
     * Method to store the parameters of a command.
     *
     * @param argument the description of a todo command
     * @param index integer that dictates which index of an array parameters should be stored.
     *          Useful only for the Deadline and Event command.
     */
    @Override
    public void setArgument(String argument, int index) {
        this.description = argument;
    }

    /**
     * Get parts of user input necessary for command execution.
     *
     * @param type boolean value that dictates which element of an array containing the parameters should
     *          be returned. Useful only for the Deadline and Event command.
     * @return the description of a todo command
     */
    @Override
    public String getArgument(boolean type) {
        return description;
    }
}
