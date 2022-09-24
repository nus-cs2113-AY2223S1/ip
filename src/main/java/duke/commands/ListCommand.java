package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int currentListSize = taskList.getCurrentListSize();
        boolean hasNoTask = (currentListSize == 0);
        if (hasNoTask) {
            ui.showEmptyListMessage();
            return;
        }
        ui.printList(taskList, currentListSize);
    }
}
