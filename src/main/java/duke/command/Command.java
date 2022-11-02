package duke.command;

import duke.parser.Parser;
import duke.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public abstract class Command extends Parser {
    protected Ui ui = new Ui();
    protected final int NO_TASK = 0;
    protected String[] splitCommand;
    protected int numberOrder = 1;
    protected int countTask;
    protected int indexTask;

    public Command() {}
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit() { return false; }
    public void updateCountFromTasks(TaskList tasks) {
        countTask = tasks.getCountTask();
    }
    public void updateTaskFromStorage(Storage storage) {
        countTask = storage.getCountTask();
    }

}
