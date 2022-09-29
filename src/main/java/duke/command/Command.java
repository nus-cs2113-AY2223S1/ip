package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public abstract class Command {

    Command() {

    }

    /**
     * Executes the command and returns the result
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeException;

    public abstract boolean isExit();

}
