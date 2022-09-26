package duke.command;

/**
 * <code>DeleteCommand</code> is a class for delete commands.
 * It stores parsed parts of user input necessary for deletion of tasks.
 */
public class DeleteCommand extends Command {

    private String taskPosition;

    /**
     * Constructor of <code>DeleteCommand</code>.
     */
    public DeleteCommand() {
        super();
    }

    /**
     * Method to store the parameters of a command.
     *
     * @param argument parsed part of user input
     * @param index integer that dictates which index of an array parameters should be stored.
     *          Useful only for the Deadline and Event command.
     */
    @Override
    public void setArgument(String argument, int index) {
        this.taskPosition = argument;
    }

    /**
     * Get parts of user input necessary for command execution.
     *
     * @param type boolean value that dictates which element of an array containing the parameters should
     *          be returned. Useful only for the Deadline and Event command.
     * @return the position of the task to be deleted
     */
    @Override
    public String getArgument(boolean type) {
        return taskPosition;
    }
}
