package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    protected boolean isExit = true;

    /**
     * Executes the action for exit command.
     *
     * @param tasks which is taken from the class TaskList to obtain
     *              the list of assignments.
     * @param ui which is taken from the class Ui to display messages.
     * @param storage which is taken from the class storage which deals.
     *                with the add and remove of data from the data file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return isExit;
    }
}
