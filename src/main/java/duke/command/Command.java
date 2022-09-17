package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * Abstract class for commands
 */
public abstract class Command {
    public abstract void execute(TaskList taskList, UI ui) throws DukeException;
}
