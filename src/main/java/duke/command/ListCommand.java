package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {
    protected int numberOrder = 1;
    protected final int NO_TASK = 0;

    /**
     * Executes the action for list command.
     *
     * @param tasks   which is taken from the class TaskList to obtain
     *                the list of assignments.
     * @param ui      which is taken from the class Ui to display messages.
     * @param storage which is taken from the class storage which deals.
     *                with the add and remove of data from the data file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> assignments = tasks.getAssignments();
        updateCountFromTasks(tasks);
        getList(assignments);
    }

    /**
     * Gets a list of tasks and displays it on the terminal for the user.
     *
     * @param assignments which is taken from the class TaskList.
     */
    public void getList(ArrayList<Task> assignments) {
        if (countTask == NO_TASK) {
            ui.showEmptyList();
        } else {
            ui.showListMessage();
            ui.showList(assignments, numberOrder, countTask);
        }
    }
}
