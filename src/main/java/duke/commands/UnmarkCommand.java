package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {

    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printLine();
        tasks.markTaskAsUndone(taskNumber);
        storage.saveData(tasks);
        ui.printLine();
    }
}
