package duke.taskmanager.commands;

import duke.UI;
import duke.taskmanager.Storage;
import duke.taskmanager.TaskList;

/**
 * User input is incomplete
 */
public class EmptyCommand extends Command{
    private final String keyword;

    /**
     * Stores the <code>Command</code> keyword for which the user has not provided sufficient details
     *
     * @param keyword part of the user input that contains the command keyword
     */
    public EmptyCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Prints which <code>Command</code> the user has not provided sufficient details
     *
     * @param tasks   stores all the user's current tasks
     * @param ui      contains the formatted outputs
     * @param storage stores the tasks after the programme closes
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.printEmptyException(keyword);
    }
}
