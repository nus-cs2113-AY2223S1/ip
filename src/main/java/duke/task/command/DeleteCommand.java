package duke.task.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * A class that represents a delete command.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructor for DeleteCommand.
     *
     * @param index Index of task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this delete command by removing the task from the list
     * and the local storage.
     * Displays a message telling user that the task has been deleted.
     *
     * @param ui Object that handles all user interaction.
     * @param tasks Object that handles and tracks all tasks that the user has added.
     * @param storage Object that handles saving the user's task into the local storage.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.deleteMessage(tasks, index);
        tasks.deleteTask(index);
        storage.rewriteFile(storage);
    }
}
