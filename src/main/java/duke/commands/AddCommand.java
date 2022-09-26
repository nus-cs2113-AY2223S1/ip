package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class AddCommand extends Command {

    private Task taskToBeAdded;

    public AddCommand(Task taskToBeAdded) {
        super(false);
        this.taskToBeAdded = taskToBeAdded;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printLine();
        tasks.addTask(taskToBeAdded);
        storage.saveData(tasks);
        ui.printLine();
    }
}
