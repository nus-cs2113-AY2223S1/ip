package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.ui.Ui;
import duke.storage.Storage;

public abstract class Command {
    public static String COMMAND_WORD;
    private static boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void checkAndSetParameters(String parameterInput) throws DukeException;

    protected abstract void checkParameters(String parameterInput) throws DukeException;

    protected abstract void setParameters(String parameterInput);

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
