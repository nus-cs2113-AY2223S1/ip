package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.DukeUI;

public class ByeCommand extends Command {

    public ByeCommand() {
        this.isExit = true;
    }

    @Override
    public void execute(DukeUI ui, Storage storage, TaskList tasks) {
        ui.printByeMessage();
    }
}
