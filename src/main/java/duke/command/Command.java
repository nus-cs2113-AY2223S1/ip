package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * <code>Command</code> is the abstract base class for the every available command in the application.
 */
public abstract class Command {
    // The valid command word used to trigger the execution.
    public static String COMMAND_WORD;
    // The boolean value to be checked by Duke to determine whether to exit the application.
    private static boolean isExit;

    /**
     * Constructor of <code>Command</code>. The isExit boolean is set to false by default.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Returns the is exit status.
     *
     * @return A boolean value to represent whether to exit the program.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Sets the is exit status.
     *
     * @param isExit A boolean value to represents whether to exit the program.
     */
    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Checks the user's input and stores necessary parameters for execution.
     *
     * @param parameterInput The extracted part of user input after the command entered.
     * @throws DukeException Exception triggered on invalid user input.
     */
    public abstract void checkAndSetParameters(String parameterInput) throws DukeException;

    /**
     * Checks the validity of the user's input. Throws exception on invalid input.
     *
     * @param parameterInput The extracted part of user input after the command entered.
     * @throws DukeException Exception triggered on invalid user input.
     */
    protected abstract void checkParameters(String parameterInput) throws DukeException;

    /**
     * Stores the necessary parameters for execution.
     *
     * @param parameterInput The extracted part of user input after the command entered.
     * @throws DukeException Exception triggered on invalid user input.
     */
    protected abstract void setParameters(String parameterInput) throws DukeException;

    /**
     * Executes the command provided by the user.
     *
     * @param taskList List of tasks stored in current execution.
     * @param ui       User interface to display messages.
     * @param storage  File storage to read, append or rewrite file.
     * @throws DukeException Exception triggered on invalid user input.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
