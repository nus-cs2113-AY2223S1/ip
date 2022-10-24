package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents a command that can be executed.
 */
public abstract class Command {
    private String input;

    Command(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    // Execute the command, store the result in the task list
    public abstract TaskList execute(TaskList tasks, Storage storage, Ui ui, String fullCommand) throws DukeException;

    // Check if the command is an exit command
    public abstract boolean isExit();


}
