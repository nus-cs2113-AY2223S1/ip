package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeIOException;
import duke.task.TaskList;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeIOException {
        ui.goodbyeMessage();
        storage.writeToFile(tasks);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
