package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.TaskNumberParser;

/**
 * Represents commands recognised by Duke
 */
public abstract class Command {

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
     * Checks if user entered command to exit Duke
     * This method is overridden in ExitCommand
     * @return true if user entered command to exit, false otherwise
     */
    public boolean isExitCommand() {
        return false;
    }
}
