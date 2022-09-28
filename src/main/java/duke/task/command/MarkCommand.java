package duke.task.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * A class that represents the mark command.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Construstor for MarkCommand.
     *
     * @param index Index of task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this mark command by marking the tasks of the index.
     * Displays a message telling the user that the task has been marked.
     *
     * @param ui      Object that handles all user interaction.
     * @param tasks   Object that handles and tracks all tasks that the user has added.
     * @param storage Object that handles saving the user's task into the local storage.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.markTarget(index);
        ui.markMessage(tasks, index);
        storage.rewriteFile(storage);
    }
}
