package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Ui.outputWithLines("Please don't go :(");
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }
}
