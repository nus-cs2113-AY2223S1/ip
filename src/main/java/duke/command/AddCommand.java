package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskFactory;

public class AddCommand extends Command {

    private final Task task;

    public AddCommand(String input) throws DukeException {
        super(input);
        task = TaskFactory.createTask(input);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addItem(task);
        ui.displayMessage("added: " + task);
    }

}
