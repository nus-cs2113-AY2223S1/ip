package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.DukeException;
import duke.Storage;

/**
 * Represents an operation to be executed by duke.
 */
public abstract class Command {
    private String input;

    /**
     * Creates the command.
     * 
     * @param input The user input
     */
    Command(String input) {
        this.input = input;
    }

    /**
     * Gets the user input for this command.
     * 
     * @return The user input
     */
    public String getInput() {
        return input;
    }

    /**
     * Executes the command, updating the state variables provided.
     * 
     * @param taskList    The list of tasks in the application
     * @param ui          The ui manager
     * @param storage     The storage manager
     * @param lastResults The previous list of tasks displayed
     * @return The list of tasks obtained from execution of this command
     * @throws DukeException Throws an exception if execution fails
     */
    public abstract TaskList execute(TaskList taskList, Ui ui, Storage storage, TaskList lastResults)
            throws DukeException;

    /**
     * Returns whether this command is a command to exit the application.
     * 
     * @return True if the command is an exit command and false otherwise
     */
    public abstract boolean isExit();
}
