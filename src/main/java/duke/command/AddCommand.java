package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class AddCommand extends Command{
    protected final int NEW_TASK_INDEX = 1;
    protected boolean isInitialEmpty = false;
    protected boolean isCountTaskEmpty = false;
    public AddCommand(String[] splitCommand) {
        this.splitCommand = splitCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> assignments = tasks.getAssignments();
        isInitialEmpty = storage.isInitialEmpty();
        updateTaskFromStorage(storage);
        checkEmptyTask();
        checkTypeOfIncrement(tasks);
        checkCommandType(splitCommand);
        addTypeOfTask(tasks, assignments);
        ui.showTypeOfTaskDetail(assignments, indexTask);
        updateCountFromTasks(tasks);
        storage.saveToFile(countTask, assignments);
    }

    public void checkTypeOfIncrement(TaskList tasks) {
        if (isInitialEmpty || isCountTaskEmpty) {
            indexTask = tasks.getIndexTask();
        } else {
            indexTask = tasks.getIndexTask() + NEW_TASK_INDEX;
        }
    }

    public void checkEmptyTask() {
        if (countTask == 0) {
            isCountTaskEmpty = true;
        }
    }

    public void addTypeOfTask(TaskList tasks, ArrayList<Task> assignments) {
        if (isEvent) {
            tasks.addEventTask(splitCommand, assignments, indexTask);
        } else if (isDeadline) {
            tasks.addDeadlineTask(splitCommand, assignments, indexTask);
        } else if (isToDo) {
            tasks.addToDoTask(splitCommand, assignments, indexTask);
        }
    }
}
