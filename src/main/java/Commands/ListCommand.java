package Commands;

import Misc.DukeException;
import Misc.Storage;
import Misc.Ui;
import Tasks.TaskList;

public class ListCommand extends Command{

    /**
     * Executes the command.
     * @param tasks Tasks list.
     * @param ui UI object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.getTasks());
    }
}
