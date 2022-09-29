package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> assignments = tasks.getAssignments();
        updateCountFromTasks(tasks);
        getList(assignments);
    }

    public void getList(ArrayList<Task> assignments) {
        if (countTask == NO_TASK) {
            ui.showEmptyList();
        } else {
            ui.showListMessage();
            ui.showList(assignments, numberOrder, countTask);
        }
    }
}
