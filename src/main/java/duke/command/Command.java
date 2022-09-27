package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public abstract class Command {

    String keyword;
    String statement;
    Boolean isExit = false;
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public Boolean isExit() {
        return isExit;
    }

}
