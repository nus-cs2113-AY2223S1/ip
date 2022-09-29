package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class DeleteCommand extends Command{
    public DeleteCommand(String[] splitCommand) {
        this.splitCommand = splitCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> assignments = tasks.getAssignments();
        tasks.deleteTask(splitCommand, assignments);
        updateCountFromTasks(tasks);
        storage.saveToFile(countTask, assignments);
    }
}
