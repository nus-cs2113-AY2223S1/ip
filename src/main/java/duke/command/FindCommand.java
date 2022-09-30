package duke.command;

/**
 * <code>FindCommand</code> is a class for find commands.
 * It stores parsed parts of user input necessary for finding tasks that contains a user input word.
 */
public class FindCommand extends Command {

    private String lookingFor;

    /**
     * Constructor of <code>FindCommand</code>.
     */
    public FindCommand() {
        super();
    }

    /**
     * Method to store the parameters of a command.
     *
     * @param argument the user input to search for among the descriptions of existing tasks
     * @param index integer that dictates which index of an array parameters should be stored.
     *          Useful only for the Deadline and Event command.
     */
    @Override
    public void setArgument(String argument, int index) {
        this.lookingFor = argument;
    }

    /**
     * Get parts of user input necessary for command execution.
     *
     * @param isFirstIndex boolean value that dictates which element of an array containing the parameters should
     *          be returned. Useful only for the Deadline and Event command.
     * @return the user input to search for among the descriptions of existing tasks
     */
    @Override
    public String getArgument(boolean isFirstIndex) {
        return lookingFor;
    }
}
