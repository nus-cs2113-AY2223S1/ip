package duke.command;

/**
 * <code>Command</code> is the super class for all the commands.
 */
public abstract class Command {

    private String keyword;
    private boolean isBye;
    private boolean isLegal = true;

    /**
     * Constructor of super class <code>Command</code>.
     */
    public Command() {
    }

    /**
     * Returns the keyword of the command. Keywords are the words that dictate what type
     * of command did the user input. E.g. list, bye...
     *
     * @return the string representing the type of the command
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Stores the type of command in the keyword string.
     *
     * @param keyword the string representing the type of command
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns the boolean isBye that shows whether the recent command is bye.
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
     * Returns boolean value showing whether user input is free of exceptions.
     * True if input is free of exceptions.
     * False if input has exceptions.
     *
     * @return a boolean value to show whether user input has exceptions
     */
    public boolean isLegal() {
        return isLegal;
    }

    /**
     * Sets the boolean <code>isLegal</code> status based on the existence of possible exceptions
     * in user input. True if the command could cause an exception and false if not.
     *
     * @param legal boolean that represents the existence of possible exceptions
     */
    public void setLegal(boolean legal) {
        isLegal = legal;
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
     * @param type boolean value that dictates which element of an array containing the parameters should
     *          be returned. Useful only for the Deadline and Event command.
     * @return the arguments needed for command execution
     */
    public abstract String getArgument(boolean type);
}
