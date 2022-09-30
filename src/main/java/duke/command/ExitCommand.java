package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ExitCommand extends Command {
    public static final String COMMAND = "bye";
    public static final String MESSAGE_HELP = "bye\t - exits the application";

    public ExitCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }

    @Override
    public boolean isExit() {
        return true;
    }

    public static String getHelpMessage() {
        return MESSAGE_HELP;
    }
}
