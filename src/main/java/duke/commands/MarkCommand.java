package duke.commands;

import duke.common.ErrorMessages;
import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.TextUi;

public class MarkCommand extends UpdateCommand {
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";

    protected String command;

    public MarkCommand(String command, String description) {
        super(description);
        this.command = command;
    }

    @Override
    public void execute(TextUi ui, TaskList tasks, Storage storage) {
        try {
            int taskIndex = checkIndexIsValid(description, tasks.getTaskCount());
            boolean isDone = command.equals(COMMAND_MARK);
            String taskDetails = tasks.markTask(taskIndex, isDone);
            ui.showMarkTaskInfo(taskDetails, isDone);
            writeToFile(ui, tasks, storage);
        } catch (NumberFormatException e) {
            ui.showCustomText(ErrorMessages.MESSAGE_ERROR_INVALID_STATUS_FORMAT);
        } catch (IndexOutOfBoundsException e) {
            ui.showCustomText(ErrorMessages.MESSAGE_ERROR_INVALID_TASK_INDEX);
        }
    }
}
