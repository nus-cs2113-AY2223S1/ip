package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class DeleteCommand extends ModifyCommand {
    public static final String COMMAND_WORD = "DELETE";

    public DeleteCommand() {
        super();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        checkTaskIndexRange(taskIndex, taskList.getSize());
        String taskDetail = taskList.deleteTask(taskIndex);
        ui.displayTaskDeletionMessage(taskDetail, taskList.getSize());
        storage.rewriteDukeFile(taskList);
    }
}
