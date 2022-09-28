package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskManager;
import duke.util.Ui;

public abstract class Command {
    private String userInput;

    Command(String userInput) {
        this.userInput = userInput;
    }

    public String getInput() {
        return userInput;
    }

    /**
     * Executes the command and returns the result
     */
    public abstract TaskManager execute(TaskManager tasks, Ui ui, Storage storage)
            throws DukeException;

    public abstract boolean isExit();

}
