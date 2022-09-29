package duke.command;

import duke.util.Storage;
import duke.util.TaskManager;
import duke.util.Ui;

public class ExitCommand extends Command {
    public static final String COMMAND = "bye";

    public ExitCommand() {

    }

    @Override
    public void execute(TaskManager tasks, Ui ui, Storage storage) {

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
