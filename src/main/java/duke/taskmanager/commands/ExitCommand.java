package duke.taskmanager.commands;

import duke.UI;
import duke.taskmanager.Storage;
import duke.taskmanager.TaskList;

/**
 * Indication to stop and exit the programme
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super();
        this.isExit = true;
    }

    /**
     * Prints exit message before exiting the programme
     *
     * @param tasks   stores all the user's current tasks
     * @param ui      contains the formatted outputs
     * @param storage stores the tasks after the programme closes
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.printExitMessage();
    }
}
