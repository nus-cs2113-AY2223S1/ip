package CommandPackage;

import ExceptionsPackage.DukeException;
import TaskPackage.TaskList;
import TaskPackage.Task;
import UiPackage.Ui;
import StoragePackage.Storage;

/*
Command to exit program
 */
public class ByeCommand extends Command {
    public ByeCommand(){}

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printExitMessage();
    }
}
