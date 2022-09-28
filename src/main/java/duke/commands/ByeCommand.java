package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Prints bye message.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Constructs constructor for Bye command.
     */
    public ByeCommand() {
    }

    /**
     * Prints bye message.
     *
     * @param taskList Used to access taskList information.
     * @param ui Used to print bye message.
     * @param storage Used to update task information in duke.txt.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showByeMessage();
    }
}
