package duke.command;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * <code>UnmarkCommand</code> is the command that marks a task as not done based on an index given.
 */
public class UnmarkCommand extends ModifyCommand {
    public static final String COMMAND_WORD = "UNMARK";

    public UnmarkCommand() {
        super();
    }

    /**
     * Checks that the task index is within the valid range of task list.
     * Mark the task in the task list as not done.
     * Display a mark as not done successful message to user.
     * Rewrite the file storage based on new task list.
     *
     * @param taskList List of tasks stored in current execution.
     * @param ui       User interface to display messages.
     * @param storage  File storage to read, append or rewrite file.
     * @throws DukeException Exception triggered on invalid user input or erroneous file operation.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        checkTaskIndexRange(taskIndex, taskList.getSize());
        Task task = taskList.unmarkTask(taskIndex);
        ui.displayTaskUnmarkedMessage(task.getTaskName());
        storage.rewriteDukeFile(taskList);
    }
}
