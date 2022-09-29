package duke.command;

import duke.parser.Parser;
import duke.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public abstract class Command extends Parser {

    protected int countTask;
    protected int indexTask;

    public Command() {}
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit() { return false; }
}
