package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class UnmarkCommand extends Command {
    private final int taskIndex;
    public static final String COMMAND = "unmark";
    public static final String MESSAGE_HELP = "unmark [INDEX]\t - mark a task as not done by index shown on list";
    private static final String MESSAGE_UNMARK = "OK, I've marked this task as not done yet:";


    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        try {
            tasks.setTask(taskIndex, false);
            ui.addLine(MESSAGE_UNMARK);
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
