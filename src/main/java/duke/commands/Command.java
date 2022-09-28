package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList Used to access taskList information.
     * @param ui Used to print confirmation or exception messages.
     * @param storage Used to update task information in duke.txt.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
