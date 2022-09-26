package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {

    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printLine();
        tasks.removeTask(taskNumber);
        storage.saveData(tasks);
        ui.printLine();
    }
}
