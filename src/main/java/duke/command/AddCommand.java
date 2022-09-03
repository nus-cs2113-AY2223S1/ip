package duke.command;

import duke.task.List;
import duke.task.Task;
import duke.ui.UI;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(List list, UI ui) {
        list.add(task);
        ui.confirmAdd(task, list);
    }
}
