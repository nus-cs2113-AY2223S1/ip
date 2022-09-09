package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.Task;
import duke.ui.UI;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UI ui) throws DukeException {
        Task removedTask = taskList.findTask(index);
        taskList.delete(index);
        ui.confirmDelete(removedTask, taskList);
    }
}
