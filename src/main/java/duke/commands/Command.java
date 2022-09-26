package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return isExit;
    }
}
