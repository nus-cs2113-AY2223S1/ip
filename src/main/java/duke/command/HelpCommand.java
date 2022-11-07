package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.DukeUI;

public class HelpCommand extends Command {

    public HelpCommand() {
        this.isExit = false;
    }

    @Override
    public void execute(DukeUI ui, Storage storage, TaskList tasks) {
        ui.printHelpPage();
    }
}
