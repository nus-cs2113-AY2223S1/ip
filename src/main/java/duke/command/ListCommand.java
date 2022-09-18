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
        Ui.line();
        Ui.outputWithoutLines("You have " + TaskList.Tasks.size() + " tasks");
        for (Task task : TaskList.Tasks) {
            Ui.outputWithoutLines(task.listTask());
        }
        Ui.line();
    }
}
