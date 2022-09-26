package duke.command;

/**
 * <code>MarkCommand</code> is a class for mark commands.
 * It stores parsed parts of user input necessary for marking of tasks to be done.
 */
public class MarkCommand extends Command {

    private String taskPosition;

    /**
     * Constructor of <code>MarkCommand</code>.
     */
    public MarkCommand() {
        super();
    }

    /**
     * Method to store the parameters of a command.
     *
     * @param argument the position of the task to be marked as done
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
     * @return the position of the task to be marked as done
     */
    @Override
    public String getArgument(boolean type) {
        return taskPosition;
    }
}
