package task.command;

import storage.Storage;
import task.TaskList;
import ui.UI;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.byeMessage();
    }
}
