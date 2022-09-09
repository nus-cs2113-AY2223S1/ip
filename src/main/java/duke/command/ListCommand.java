package duke.command;

import duke.task.TaskList;
import duke.ui.UI;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, UI ui) {
        ui.printList(taskList);
    }
}
