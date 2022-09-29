package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    protected boolean isExit = true;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return isExit;
    }
}
