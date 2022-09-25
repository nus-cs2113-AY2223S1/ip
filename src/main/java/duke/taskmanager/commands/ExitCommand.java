package duke.taskmanager.commands;

import duke.UI;
import duke.taskmanager.Storage;
import duke.taskmanager.TaskList;

public class ExitCommand extends Command {
    public ExitCommand() {
        super();
        this.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.printExitMessage();
    }
}
