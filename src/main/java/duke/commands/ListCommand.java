package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Prints the task list.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Constructs constructor for List command.
     */
    public ListCommand() {
    }

    /**
     * Prints task list if list is non-empty. Otherwise, prints empty list message.
     *
     * @param taskList Used to access taskList information.
     * @param ui Used to print non-empty task list or empty task message.
     * @param storage Used to update task information in duke.txt.
     */
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
