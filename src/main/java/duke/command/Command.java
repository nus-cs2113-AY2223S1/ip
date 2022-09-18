package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidTaskNumberDukeException;
import duke.task.TaskList;

public abstract class Command {

    public Command() {
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public int extractTaskNumber(String arguments) throws DukeException{
        try {
            return Integer.parseInt(arguments.trim());
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberDukeException();
        }
    }

    public boolean isExitCommand() {
        return false;
    }
}
