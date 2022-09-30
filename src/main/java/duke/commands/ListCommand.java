package duke.commands;

import duke.Ui;

/**
 * When executed, it lists all tasks in the taskList
 */
public class ListCommand extends Command {
    public static final String LIST_COMMAND = "list";

    public ListCommand() {
        Ui.printTaskList();
    }
}
