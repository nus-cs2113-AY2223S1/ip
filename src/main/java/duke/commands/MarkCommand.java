package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {

    private int taskNumber;

    public MarkCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printLine();
        tasks.markTaskAsDone(taskNumber);
        storage.saveData(tasks);
        ui.printLine();
    }
}
