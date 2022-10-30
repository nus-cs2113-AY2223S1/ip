package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ListCommand extends Command {
    public static final String COMMAND = "list";
    public static final String MESSAGE_HELP = "list\t - list out all the tasks added to list";
    private static final String MESSAGE_LIST = "Beep beep, listing out the tasks....Loading.....";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayMessage(MESSAGE_LIST);
        tasks.generateTaskList();

        ui.addLine(tasks.getMessages());
        ui.printUi();
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
