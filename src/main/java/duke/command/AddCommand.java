package duke.command;

import duke.task.TaskList;
import duke.task.Task;
import duke.ui.UI;

/**
 * A representation of a command to add a new task.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructor for AddCommand.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes add command.
     *
     * @param taskList The list of tasks to work with.
     * @param ui       The Duke user interface for user to interact with.
     */
    @Override
    public void execute(TaskList taskList, UI ui) {
        taskList.add(task);
        ui.confirmAdd(task, taskList);
    }
}
