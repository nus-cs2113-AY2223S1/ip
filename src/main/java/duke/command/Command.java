package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.DukeException;
import duke.Storage;

public abstract class Command {
    private String input;

    Command(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
