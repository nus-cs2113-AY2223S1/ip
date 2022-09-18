package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidTaskNumberDukeException;
import duke.task.TaskList;

public abstract class Command {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public Command() {
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public int extractTaskNumber(String arguments) throws DukeException{
        try {
            int taskNumber = Integer.parseInt(arguments.trim());
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberDukeException();
        }
    }

    public boolean isExitCommand() {
        return false;
    }
}
