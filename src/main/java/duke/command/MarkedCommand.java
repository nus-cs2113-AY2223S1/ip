package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class MarkedCommand extends Command {
    public static final String COMMAND = "marked";
    public static final String MESSAGE_HELP = "marked\t "
           + "- marked the last task in the list as done, only for loading data, not a user functionality";

    public MarkedCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        try {
            tasks.setLastTask(true);
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
