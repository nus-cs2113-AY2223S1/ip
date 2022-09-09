package duke.command;

import duke.exception.DukeException;
import duke.task.List;
import duke.task.TaskList;
import duke.ui.UI;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(List list, UI ui) throws DukeException {
        TaskList removedTask = list.findTask(index);
        list.delete(index);
        ui.confirmDelete(removedTask, list);
    }
}
