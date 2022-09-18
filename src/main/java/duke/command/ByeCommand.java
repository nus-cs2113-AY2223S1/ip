package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "BYE";

    public ByeCommand() {
        super();
    }

    @Override
    public void checkAndSetParameters(String parameterInput) {
    }

    @Override
    protected void checkParameters(String parameterInput) {
    }

    @Override
    protected void setParameters(String parameterInput) {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        setExit(true);
        ui.displayExitMessage();
    }
}
