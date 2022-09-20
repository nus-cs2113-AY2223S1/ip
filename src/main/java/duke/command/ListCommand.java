package duke.command;

import duke.Ui;

public class ListCommand extends Command {

    public ListCommand(String[] commands) {
        super();
        executeCommand(commands);
    }

    @Override
    public void executeCommand(String[] commands) {
        Ui.printAllTasks();
    }
}
