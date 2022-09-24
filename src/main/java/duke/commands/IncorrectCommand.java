package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

public class IncorrectCommand extends Command {
    public IncorrectCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showUndefinedCommandMessage();
    }
}
