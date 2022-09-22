package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents command for listing all tasks
 */
public class ListCommand extends Command {

    public ListCommand() {
    }

    /**
     * Informs user of total number of tasks, then lists all tasks in taskList
     *
     * @param taskList ArrayList containing current tasks
     * @param ui       Ui object for communicating with user
     * @param storage  Storage object for loading and saving tasks
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.output("You have " + taskList.tasks.size() + " tasks");
        for (Task task : taskList.tasks) {
            ui.output(task.listTask(taskList.tasks));
        }
    }
}
