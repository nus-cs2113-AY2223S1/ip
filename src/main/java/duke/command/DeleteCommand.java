package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * <code>DeleteCommand</code> is the command that deletes a task based on an index given.
 */
public class DeleteCommand extends ModifyCommand {
    public static final String COMMAND_WORD = "DELETE";

    public DeleteCommand() {
        super();
    }

    /**
     * Check that the task index is within the valid range of task list.
     * Delete the task from task list.
     * Display a deletion successful message to user.
     * Rewrite the file storage based on new task list.
     *
     * @param taskList List of tasks stored in current execution.
     * @param ui       User interface to display messages.
     * @param storage  File storage to read, append or rewrite file.
     * @throws DukeException Exception triggered on erroneous file operation.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        checkTaskIndexRange(taskIndex, taskList.getSize());
        String taskDetail = taskList.deleteTask(taskIndex);
        ui.displayTaskDeletionMessage(taskDetail, taskList.getSize());
        storage.rewriteDukeFile(taskList);
    }
}
