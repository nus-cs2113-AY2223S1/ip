package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {
    protected int numberOrder = 1;
    protected final int NO_TASK = 0;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> assignments = tasks.getAssignments();
        updateTaskFromTasks(tasks);
        getList(assignments, countTask, ui);
    }

    public void updateTaskFromTasks(TaskList tasks) {
        countTask = tasks.getCountTask();
    }

    public void getList(ArrayList<Task> assignments, int indexTask, Ui ui) {
        if (indexTask == NO_TASK) {
            ui.showEmptyList();
        } else {
            ui.showList(assignments, numberOrder, indexTask);
        }
    }
}
