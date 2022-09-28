package task.command;

import storage.Storage;
import task.TaskList;
import ui.UI;

/**
 * A class that represents a bye command
 */
public class ByeCommand extends Command {

    /**
     * Executes the bye command by displaying the exit message
     * when the user quits the program.
     *
     * @param ui Object that handles all user interaction.
     * @param tasks Object that handles and tracks all tasks that the user has added.
     * @param storage Object that handles saving the user's task into the local storage.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.byeMessage();
    }
}
