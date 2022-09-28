package task.command;

import storage.Storage;
import task.TaskList;
import ui.UI;

/**
 * A class that represents an invalid command.
 */
public class InvalidCommand extends Command {
    /**
     * Executes the invalid command by displaying a message telling the user
     * that their command is invalid.
     *
     * @param ui Object that handles all user interaction.
     * @param tasks Object that handles and tracks all tasks that the user has added.
     * @param storage Object that handles saving the user's task into the local storage.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.invalidCommand();
    }
}
