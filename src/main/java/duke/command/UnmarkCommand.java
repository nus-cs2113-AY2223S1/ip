package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * A representation of a command to mark a task as undone.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param index The index of the task to be mark as undone.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes unmark done command.
     *
     * @param taskList The list of tasks to work with.
     * @param ui       The Duke user interface for user to interact with.
     * @throws DukeException If there is a exception occurs
     */
    @Override
    public void execute(TaskList taskList, UI ui) throws DukeException {
        taskList.unmarkDone(index);
        ui.confirmUnmark(taskList.getTask(index));
    }
}
