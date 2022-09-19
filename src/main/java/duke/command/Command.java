package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidTaskNumberDukeException;
import duke.task.TaskList;

/**
 * Represents command to be executed by Duke
 */
public abstract class Command {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public Command() {
    }

    /**
     * Executes the command
     * This method is overridden in each specific command
     * @param taskList ArrayList containing current tasks
     * @param ui Ui object for communicating with user
     * @param storage Storage object for loading and saving tasks
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Extracts task number from user input
     * Informs user if input is not an int or out of bounds of taskList
     * @param arguments user String input containing task number
     * @return task number
     * @throws DukeException if input is not int or out of bounds of taskList
     */
    public int extractTaskNumber(String arguments) throws DukeException{
        try {
            int taskNumber = Integer.parseInt(arguments.trim());
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberDukeException();
        }
    }

    /**
     * Checks if user entered command to exit Duke
     * This method is overridden in ExitCommand
     * @return true if user entered command to exit, false otherwise
     */
    public boolean isExitCommand() {
        return false;
    }
}
