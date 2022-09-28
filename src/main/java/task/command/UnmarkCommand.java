package task.command;

import storage.Storage;
import task.TaskList;
import ui.UI;

public class UnmarkCommand extends Command {
    private int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage)  {
        tasks.unmarkTarget(index);
        ui.markMessage(tasks, index);
        tasks.rewriteFile(storage);
    }
}
