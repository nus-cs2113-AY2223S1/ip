package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents command for trying to help user
 */
public class HelpCommand extends Command {

    public HelpCommand() {
    }

    /**
     * Informs user to ask for help again using different command
     *
     * @param taskList ArrayList containing current tasks
     * @param ui       Ui object for communicating with user
     * @param storage  Storage object for loading and saving tasks
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.output("You asked for help, but I don't feel like helping ;p\n" +
                "Maybe try saying the magic word?");
    }
}
