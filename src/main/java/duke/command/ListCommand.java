package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents command for listing all tasks
 */
public class ListCommand extends Command{

    public ListCommand() {
    }

    /**
     * Informs user of total number of tasks,
     * then lists all tasks in taskList
     * @param taskList ArrayList containing current tasks
     * @param ui Ui object for communicating with user
     * @param storage Storage object for loading and saving tasks
     */
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
