package task.command;

import storage.Storage;
import task.TaskList;
import ui.UI;

public class MarkCommand extends Command {
    private int index;
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.markTarget(index);
        ui.markMessage(tasks, index);
        tasks.rewriteFile(storage);
    }
}
