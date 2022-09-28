package duke.task.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * An abstract class that can represent all other commands within Duke.
 */
public abstract class Command {

    /**
     * An abstract method represents the execution of a command.
     *
     * @param ui Object that handles all user interaction.
     * @param tasks Object that handles and tracks all tasks that the user has added.
     * @param storage Object that handles saving the user's task into the local storage.
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage);
}
