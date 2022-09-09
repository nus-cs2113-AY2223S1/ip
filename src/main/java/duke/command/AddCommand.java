package duke.command;

import duke.task.TaskList;
import duke.task.Task;
import duke.ui.UI;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, UI ui) {
        taskList.add(task);
        ui.confirmAdd(task, taskList);
    }
}
