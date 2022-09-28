package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Represents an non-executable command.
 */
public class IncorrectCommand extends Command {
    /**
     * Constructs constructor for Incorrect command.
     */
    public IncorrectCommand() {
    }

    /**
     * Prints undefined command message.
     *
     * @param taskList Used to access taskList information.
     * @param ui Used to print undefined command message.
     * @param storage Used to update task information in duke.txt.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showUndefinedCommandMessage();
    }
}
