package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * An abstract class of command
 */
public abstract class Command {
    /**
     * Executes command.
     *
     * @param taskList The list of tasks to be worked with.
     * @param ui The Duke user interface for user to interact with.
     * @throws DukeException If there is an exception occurs.
     */
    public abstract void execute(TaskList taskList, UI ui) throws DukeException;
}
