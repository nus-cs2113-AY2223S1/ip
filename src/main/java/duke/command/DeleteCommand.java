package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class DeleteCommand extends Command {
    private final int taskIndex;
    public static final String COMMAND = "delete";
    public static final String MESSAGE_HELP = "delete [INDEX]\t - delete a task from the list by index";
    private final String MESSAGE_DELETE = "Noted. I've removed this task: ";

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        try {
            tasks.deleteTask(taskIndex);
            ui.addLine(MESSAGE_DELETE);
            ui.addLine(tasks.getMessages());
            ui.printUi();
        } catch (DukeException e) {
            ui.displayMessage(e.getErrorMessage());
        }
        tasks.clearBuffer();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public static String getHelpMessage() {
        return MESSAGE_HELP;
    }
}
