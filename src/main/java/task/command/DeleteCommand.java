package task.command;

import storage.Storage;
import task.TaskList;
import ui.UI;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.deleteMessage(tasks, index);
        tasks.deleteTask(index);
        tasks.rewriteFile(storage);
    }
}
