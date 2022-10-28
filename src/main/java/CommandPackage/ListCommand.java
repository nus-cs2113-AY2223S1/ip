package CommandPackage;

import ExceptionsPackage.DukeException;
import TaskPackage.TaskList;
import TaskPackage.Task;
import TaskPackage.Deadline;
import TaskPackage.Event;
import TaskPackage.Todo;
import UiPackage.Ui;
import StoragePackage.Storage;

/*
Command which lists all Tasks in list
 */
public class ListCommand extends Command {
    public ListCommand(){}

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printTasks(tasks);
    }
}
