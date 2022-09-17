package duke.command;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class UnmarkCommand extends ModifyCommand {
    public static final String COMMAND_WORD = "UNMARK";
    private static final boolean IS_UNMARK_METHOD = false;

    public UnmarkCommand() {
        super();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        checkTaskIndexRange(taskIndex, taskList.getSize());
        Task task = taskList.unmarkTask(taskIndex);
        ui.displayMarkOrUnmarkMessage(task.getTaskName(), IS_UNMARK_METHOD);
        storage.rewriteDukeFile(taskList);
    }
}
