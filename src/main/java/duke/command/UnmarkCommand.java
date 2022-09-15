package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.UI;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UI ui) throws DukeException {
        taskList.unmarkDone(index);
        ui.confirmUnmark(taskList.getTask(index));
    }
}
