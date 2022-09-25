package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;

public abstract class Command {

    String keyword;
    String statement;
    Boolean isExit = false;
    public abstract void execute(ArrayList<Task> tasks, Ui ui, Storage storage);

    public Boolean isExit() {
        return isExit;
    }

}
