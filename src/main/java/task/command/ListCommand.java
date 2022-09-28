package task.command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.UI;

import java.util.ArrayList;

/**
 * A class that represents a list command.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying the list of all tasks current
     * in the list, or tells user that the list is empty.
     *
     * @param ui Object that handles all user interaction.
     * @param tasks Object that handles and tracks all tasks that the user has added.
     * @param storage Object that handles saving the user's task into the local storage.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ArrayList<Task> list = tasks.getTasks();
        ui.printList(list);
    }
}
