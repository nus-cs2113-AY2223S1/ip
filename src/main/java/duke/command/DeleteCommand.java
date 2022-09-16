package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(String input) {
        super(input);
        index = Integer.parseInt(input.substring("delete".length()).trim());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task toDelete = taskList.getItem(index);
        taskList.deleteItem(index);
        ui.displayMessage(String.format("OK, I've deleted %s", toDelete));
    }

}
