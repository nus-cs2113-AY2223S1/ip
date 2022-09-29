package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskManager;
import duke.util.Ui;

public class MarkedCommand extends Command {
    public static final String COMMAND = "marked";

    public MarkedCommand() {

    }

    @Override
    public void execute(TaskManager tasks, Ui ui, Storage storage) {

        try {
            tasks.setTask(-1, true);
        } catch (DukeException e) {
            ui.displayMessage(e.getErrorMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
