package duke.commands;

import duke.common.ErrorMessages;
import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.TextUi;

public class DeleteCommand extends UpdateCommand {
    public static final String COMMAND = "delete";

    public DeleteCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TextUi ui, TaskList tasks, Storage storage) {
        try {
            int taskIndex = checkIndexIsValid(description, tasks.getTaskCount());
            String taskDetails = tasks.deleteTask(taskIndex);
            ui.showDeleteTaskInfo(taskDetails, tasks.getTaskCount());
            writeToFile(ui, tasks, storage);
        } catch (NumberFormatException e) {
            ui.showCustomText(ErrorMessages.MESSAGE_ERROR_INVALID_DELETE_FORMAT);
        } catch (IndexOutOfBoundsException e) {
            ui.showCustomText(ErrorMessages.MESSAGE_ERROR_INVALID_TASK_INDEX);
        }
    }
}
