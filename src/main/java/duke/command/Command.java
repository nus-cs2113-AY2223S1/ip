package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.UI;

public abstract class Command {
    public abstract void execute(TaskList taskList, UI ui) throws DukeException;
}
