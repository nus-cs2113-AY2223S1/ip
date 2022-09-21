package duke.commands;

import duke.common.InfoMessages;
import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.TextUi;

/**
 * Represents a bye command object that will execute the operations for Bye command.
 */
public class ByeCommand extends Command {
    public static final String COMMAND = "bye";

    /**
     * Executes the operations related to the command.
     *
     * @param ui An instance of the TextUi class.
     * @param tasks An instance of the TaskList class.
     * @param storage An instance of the Storage class.
     */
    @Override
    public void execute(TextUi ui, TaskList tasks, Storage storage) {
        ui.showCustomText(InfoMessages.MESSAGE_INFO_GOODBYE);
    }
}
