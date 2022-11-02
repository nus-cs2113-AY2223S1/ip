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

    /**
     * Executes the action for delete task command.
     *
     * @param tasks which is taken from the class TaskList to obtain
     *              the list of assignments.
     * @param ui which is taken from the class Ui to display messages.
     * @param storage which is taken from the class storage which deals.
     *                with the add and remove of data from the data file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> assignments = tasks.getAssignments();
        tasks.deleteTask(splitCommand, assignments);
        updateCountFromTasks(tasks);
        storage.saveToFile(countTask, assignments);
    }
}
