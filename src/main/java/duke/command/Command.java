package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    // execute the command, store the result in the task list
    public abstract TaskList execute(TaskList tasks, Storage storage, Ui ui);


}
