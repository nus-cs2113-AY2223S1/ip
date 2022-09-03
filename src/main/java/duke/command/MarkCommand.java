package duke.command;

import duke.DukeException;
import duke.task.List;
import duke.ui.UI;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(List list, UI ui) throws DukeException {
        list.markDone(index);
        ui.confirmMark(list.findTask(index));
    }
}
