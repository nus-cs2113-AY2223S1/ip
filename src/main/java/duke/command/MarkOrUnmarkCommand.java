package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class MarkOrUnmarkCommand extends Command {
    public MarkOrUnmarkCommand(String[] splitCommand) {
        this.splitCommand = splitCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> assignments = tasks.getAssignments();
        tasks.markOrUnmarkTask(splitCommand, assignments, isMark, ui);
        updateCountFromTasks(tasks);
        storage.saveToFile(countTask, assignments);
    }

}
