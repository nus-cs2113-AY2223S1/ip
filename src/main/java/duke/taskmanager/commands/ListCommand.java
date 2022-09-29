package duke.taskmanager.commands;

import duke.UI;
import duke.taskmanager.Storage;
import duke.taskmanager.TaskList;

/**
 * List out all current tasks
 */
public class ListCommand extends Command {
    /**
     * Lists all current tasks in the <code>TaskList</code>
     *
     * @param tasks   stores all the user's current tasks
     * @param ui      contains the formatted outputs
     * @param storage stores the tasks after the programme closes
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.printList(tasks);
    }
}
