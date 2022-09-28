package Duke.Commands;

import Duke.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        Ui.printTaskList();
    }
}
