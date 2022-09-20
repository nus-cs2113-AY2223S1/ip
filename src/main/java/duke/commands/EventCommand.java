package duke.commands;

import duke.common.ErrorMessages;
import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.TextUi;

public class EventCommand extends AddCommand {
    public static final String COMMAND = "event";
    private static final String DELIMITER = " /at ";

    public EventCommand(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public void execute(TextUi ui, TaskList tasks, Storage storage) {
        try {
            String[] descriptionSplits = description.split(DELIMITER);
            Task task = tasks.addEvent(descriptionSplits[0], descriptionSplits[1], isDone);
            ui.showAddTaskInfo(task.getTaskDetails(), tasks.getTaskCount());
            writeToFile(ui, tasks, storage);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showCustomText(ErrorMessages.MESSAGE_ERROR_INVALID_EVENT_FORMAT);
        }
    }
}
