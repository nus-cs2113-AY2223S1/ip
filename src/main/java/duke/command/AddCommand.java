package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskManager;
import duke.util.Ui;
import duke.util.asset.Task;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(String input ) throws DukeException {
        super(input);
        task = new Task(input);
    }

    public TaskManager execute(TaskManager tasks, Ui ui, Storage storage) {
        return new TaskManager();
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
