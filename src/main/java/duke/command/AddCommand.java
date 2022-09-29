package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.asset.Task;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);

        if(storage.isLoaded()) {
            ui.addLine(tasks.getMessages());
            ui.printUi();
        }

        tasks.clearBuffer();
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
