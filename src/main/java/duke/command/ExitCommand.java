package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents command for exiting Duke
 */
public class ExitCommand extends Command {

    /**
     * Informs user that Duke is terminating
     * @param taskList ArrayList containing current tasks
     * @param ui Ui object for communicating with user
     * @param storage Storage object for loading and saving tasks
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.output("Please don't go :(");
    }

    /**
     * Updates Duke that user has entered command to exit
     * @return true when Duke checks if user wants to exit
     */
    @Override
    public boolean isExitCommand() {
        return true;
    }
}
