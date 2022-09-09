package duke.command;

import duke.task.List;
import duke.task.TaskList;
import duke.ui.UI;

public class AddCommand extends Command {
    private final TaskList task;

    public AddCommand(TaskList taskList) {
        this.task = taskList;
    }

    @Override
    public void execute(List list, UI ui) {
        list.add(task);
        ui.confirmAdd(task, list);
    }
}
