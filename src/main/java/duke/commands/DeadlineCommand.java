package duke.commands;

import duke.common.ErrorMessages;
import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.TextUi;

import java.time.format.DateTimeParseException;

public class DeadlineCommand extends AddCommand {
    public static final String COMMAND = "deadline";
    private static final String DELIMITER = " /by ";

    public DeadlineCommand(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public void execute(TextUi ui, TaskList tasks, Storage storage) {
        try {
            String[] descriptionSplits = description.split(DELIMITER);
            Task task = tasks.addDeadline(descriptionSplits[0], descriptionSplits[1], isDone);
            ui.showAddTaskInfo(task.getTaskDetails(), tasks.getTaskCount());
            writeToFile(ui, tasks, storage);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            ui.showCustomText(ErrorMessages.MESSAGE_ERROR_INVALID_DEADLINE_FORMAT);
        }
    }
}
