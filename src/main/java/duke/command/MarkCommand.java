package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.UI;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UI ui) throws DukeException {
        taskList.markDone(index);
        ui.confirmMark(taskList.getTask(index));
    }
}
