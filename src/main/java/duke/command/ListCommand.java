package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class ListCommand extends Command{

    public ListCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.line();
        ui.output("You have " + taskList.tasks.size() + " tasks");
        for (Task task : taskList.tasks) {
            ui.output(task.listTask(taskList.tasks));
        }
        ui.line();
    }
}
