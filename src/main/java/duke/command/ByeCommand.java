package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeIOException;
import duke.task.TaskList;

public class ByeCommand extends Command {

    /**
     *  Prints Bye Message and saves Tasks to Storage
     *
     * @param tasks TaskList of tasks
     * @param ui User Interface
     * @param storage Storage to load and save tasks
     * @throws DukeIOException Exception when writeToFile has exceptions
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeIOException {
        ui.goodbyeMessage();
        storage.writeToFile(tasks);
    }

    /**
     * Returns True as command is ByeCommand
     *
     * @return boolean True
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
