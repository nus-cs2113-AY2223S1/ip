package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents command for greeting user
 */
public class HiCommand extends Command {

    public HiCommand() {
        super();
    }

    /**
     * Responds to user's greeting
     *
     * @param taskList ArrayList containing current tasks
     * @param ui       Ui object for communicating with user
     * @param storage  Storage object for loading and saving tasks
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.output("Howdy!");
    }

}
