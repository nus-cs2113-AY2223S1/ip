package duke.task.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * A class representing an unmark command.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param index Index of task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this unmark command by unmarking the tasks of the index.
     * Displays a message telling the user that the task has been unmarked.
     *
     * @param ui Object that handles all user interaction.
     * @param tasks Object that handles and tracks all tasks that the user has added.
     * @param storage Object that handles saving the user's task into the local storage.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage)  {
        tasks.unmarkTarget(index);
        ui.markMessage(tasks, index);
        storage.rewriteFile(storage);
    }
}
