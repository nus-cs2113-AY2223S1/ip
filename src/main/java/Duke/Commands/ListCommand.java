package Duke.Commands;

import Duke.Ui;

public class ListCommand extends Command {
    public static final String LIST_COMMAND = "list";

    public ListCommand() {
        Ui.printTaskList();
    }
}
