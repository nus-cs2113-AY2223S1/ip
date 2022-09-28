package task.command;

import storage.Storage;
import task.TaskList;
import ui.UI;

public abstract class Command {

    public abstract void execute(TaskList tasks, UI ui, Storage storage);
}
