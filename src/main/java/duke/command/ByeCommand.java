package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * <code>ByeCommand</code> is the command that triggers an exit of program.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "BYE";

    /**
     * Constructor of <code>ByeCommand</code>
     */
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

    /**
     * Executes the bye command.
     * Sets the exit boolean to true to exit the program from main method.
     *
     * @param taskList Unused.
     * @param ui       User Interface to display messages.
     * @param storage  Unused.
     * @throws DukeException Unused.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        setExit(true);
        ui.displayExitMessage();
    }
}
