package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


public abstract class Command {

    String keyword;
    String statement;
    Boolean isExit = false;
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public Boolean isExit() {
        return isExit;
    }

}
