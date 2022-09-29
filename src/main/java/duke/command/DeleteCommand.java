package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class DeleteCommand extends Command {
    private final int taskIndex;
    public static final String COMMAND = "delete";
    private final String MESSAGE_DELETE = "Noted. I've removed this task: ";

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayMessage(MESSAGE_DELETE);

        tasks.deleteTask(taskIndex);

        ui.addLine(tasks.getMessages());
        ui.printUi();
        tasks.clearBuffer();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
