package CommandPackage;

import ExceptionsPackage.DukeException;
import StoragePackage.Storage;
import TaskPackage.TaskList;
import UiPackage.Ui;

public class NullCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
