package duke.commands;

import duke.common.InfoMessages;
import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.TextUi;

public class ListCommand extends Command {
    public static final String COMMAND = "list";
    protected static final String EMPTY_STRING = "";

    public ListCommand() {}

    public void execute(TextUi ui, TaskList tasks, Storage storage) {
        String tasksList = tasks.listTasks();
        if (tasksList.equals(EMPTY_STRING)) {
            ui.showCustomText(InfoMessages.MESSAGE_INFO_LIST_EMPTY);
            return;
        }
        ui.showTaskList(tasksList, InfoMessages.MESSAGE_INFO_LIST);
    }
}
