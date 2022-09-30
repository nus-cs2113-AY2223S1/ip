package Commands;

import Misc.DukeException;
import Misc.Storage;
import Misc.Ui;
import Tasks.TaskList;

public class ExitCommand extends Command {

    /**
     * Executes the command.
     * @param tasks Tasks list.
     * @param ui UI object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
