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

    public abstract TaskList execute(TaskList taskList, Ui ui, Storage storage, TaskList lastResults)
            throws DukeException;

    public abstract boolean isExit();
}
