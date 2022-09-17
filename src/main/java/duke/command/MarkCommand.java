package duke.command;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class MarkCommand extends ModifyCommand {
    public static final String COMMAND_WORD = "MARK";
    private static final boolean IS_MARK_METHOD = true;

    public MarkCommand() {
        super();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        checkTaskIndexRange(taskIndex, taskList.getSize());
        Task task = taskList.markTask(taskIndex);
        ui.displayMarkOrUnmarkMessage(task.getTaskName(), IS_MARK_METHOD);
        storage.rewriteDukeFile(taskList);
    }
}
