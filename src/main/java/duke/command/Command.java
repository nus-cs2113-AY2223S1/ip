package duke.command;

/**
 * <code>Command</code> is the super class for all the commands.
 */
public abstract class Command {

    private String commandType;
    private boolean isBye;

    /**
     * Constructor of super class <code>Command</code>.
     */
    public Command() {
    }

    /**
     * Returns the type of the command. E.g. list, bye,...
     *
     * @return the string representing the type of the command
     */
    public String getType() {
        return commandType;
    }

    /**
     * Stores the type of command in the commandType string.
     *
     * @param commandType the string representing the type of command
     */
    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    /**
     * Returns the boolean <code>isBye</code> that shows whether the recent command is bye.
     *
     * @return a boolean variable that shows whether a command is bye
     */
    public boolean isBye() {
        return isBye;
    }

    /**
     * Sets isBye status depending on whether a command is bye.
     * True if command is bye.
     * False if command is not bye.
     *
     * @param isBye a boolean variable that shows whether a command is bye
     */
    public void setBye(boolean isBye) {
        this.isBye = isBye;
    }

    /**
     * Method to store the parameters of a command.
     *
     * @param argument parsed part of user input
     * @param index integer that dictates which index of an array parameters should be stored.
     *          Useful only for the Deadline and Event command.
     */
    public abstract void setArgument(String argument, int index);

    /**
     * Get parts of user input necessary for command execution.
     *
     * @param isFirstIndex boolean value that dictates which element of an array containing the parameters should
     *          be returned. Useful only for the Deadline and Event command.
     * @return the arguments needed for command execution
     */
    public abstract String getArgument(boolean isFirstIndex);
}
