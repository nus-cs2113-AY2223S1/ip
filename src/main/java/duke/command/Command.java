package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.DukeUI;

public abstract class Command {
    protected String userInput;
    protected boolean isExit;

    public abstract void execute(DukeUI ui, Storage storage, TaskList tasks);

    public boolean getExitStatus() {
        return isExit;
    }
}
