package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents command for invalid input from user
 */
public class InvalidCommand extends Command{

    public InvalidCommand() {
    }

    /**
     * Informs user of invalid input command and how to ask for help
     * @param taskList ArrayList containing current tasks
     * @param ui Ui object for communicating with user
     * @param storage Storage object for loading and saving tasks
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.output("Invalid input. Boo! Please type help for help");
    }
}
