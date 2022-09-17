package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.Task;
import duke.ui.UI;

/**
 * A representation of a command to delete a task.
 */
public class DeleteCommand extends Command {
    private final int index;
    /**
     * Constructor for DeleteCommand.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes delete command.
     *
     * @param taskList The list of tasks to work with.
     * @param ui The Duke user interface for user to interact with.
     */
    @Override
    public void execute(TaskList taskList, UI ui) throws DukeException {
        Task removedTask = taskList.getTask(index);
        taskList.delete(index);
        ui.confirmDelete(removedTask, taskList);
    }
}
