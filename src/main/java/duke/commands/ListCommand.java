package duke.commands;

import duke.common.InfoMessages;
import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.TextUi;

/**
 * Represents a list command object that will execute the operations for List command.
 */
public class ListCommand extends Command {
    public static final String COMMAND = "list";
    protected static final String EMPTY_STRING = "";

    /**
     * Executes the operations related to the command.
     *
     * @param ui An instance of the TextUi class.
     * @param tasks An instance of the TaskList class.
     * @param storage An instance of the Storage class.
     */
    public void execute(TextUi ui, TaskList tasks, Storage storage) {
        String tasksList = tasks.listTasks();
        if (tasksList.equals(EMPTY_STRING)) {
            ui.showCustomText(InfoMessages.MESSAGE_INFO_LIST_EMPTY);
            return;
        }
        ui.showTaskList(tasksList, InfoMessages.MESSAGE_INFO_LIST);
    }
}
