package duke.task.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;

import java.util.ArrayList;

/**
 * A class that represents a find command.
 */
public class FindCommand extends Command {

    private final String target;

    /**
     * Constructor for FindCommand.
     *
     * @param target String that user wants to find in list.
     */
    public FindCommand(String target) {
        this.target = target;
    }

    /**
     * Executes this find command by searching the list of tasks for any
     * occurence of the target string.
     * Displays the list of matching items or shows a message that no matching
     * items are found.
     *
     * @param ui      Object that handles all user interaction.
     * @param tasks   Object that handles and tracks all tasks that the user has added.
     * @param storage Object that handles saving the user's task into the local storage.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ArrayList<Task> found = tasks.findInList(target);
        ui.printFound(found);
    }
}
