package duke.command;

import duke.task.TaskList;
import duke.ui.UI;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, UI ui) {
        ui.printExitMessage();
    }
}
