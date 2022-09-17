package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.UI;
/**
 * A representation of a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;
    /**
     * Constructor for MarkCommand.
     *
     * @param index The index of the task to be mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }
    /**
     * Executes mark done command.
     *
     * @param taskList The list of tasks to work with.
     * @param ui The Duke user interface for user to interact with.
     * @throws DukeException If there is a exception occurs
     */
    @Override
    public void execute(TaskList taskList, UI ui) throws DukeException {
        taskList.markDone(index);
        ui.confirmMark(taskList.getTask(index));
    }
}
