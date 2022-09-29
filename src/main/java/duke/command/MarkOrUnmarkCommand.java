package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class MarkOrUnmarkCommand extends Command {

    protected String[] splitCommand;

    public MarkOrUnmarkCommand(String[] splitCommand) {
        this.splitCommand = splitCommand;
    }

    /**
     * Executes the action for mark or unmark command.
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
        tasks.markOrUnmarkTask(splitCommand, assignments, isMark, ui);
        updateTaskFromTasks(tasks);
        storage.saveToFile(countTask, assignments);
    }

    public void updateTaskFromTasks(TaskList tasks) {
        countTask = tasks.getCountTask();
    }
}
