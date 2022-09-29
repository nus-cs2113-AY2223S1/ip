package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command to be executed.
 * The </code> Command </code> abstract class provides the abstract methods to be implemented
 * Each of the Command child class has different constructor depending on the information needed to execute the particular command
 */
public abstract class Command {

    Command() {

    }

    /**
     * Executes the command and modify the tasks list
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeException;

    public abstract boolean isExit();

}
