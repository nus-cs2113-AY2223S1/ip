package duke.command;

import duke.util.Storage;
import duke.util.TaskManager;
import duke.util.Ui;

public class ListCommand extends Command {
    public static final String COMMAND = "list";
    private static final String MESSAGE_LIST = "Beep beep, listing out the tasks....Loading.....";

    @Override
    public void execute(TaskManager tasks, Ui ui, Storage storage) {
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

}
