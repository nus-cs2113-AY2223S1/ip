package duke.command;

import duke.task.TaskList;
import duke.ui.UI;

/**
 * A representation of a command to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * Executes exit command.
     *
     * @param taskList The list of tasks.
     * @param ui       The Duke user interface for user to interact with.
     */
    @Override
    public void execute(TaskList taskList, UI ui) {
        ui.printExitMessage();
    }
}
