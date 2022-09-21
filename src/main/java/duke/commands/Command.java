package duke.commands;

import duke.common.ErrorMessages;
import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.TextUi;

import java.io.IOException;

/**
 * Represents an object that can be inherited by other command objects.
 */
public abstract class Command {

    /**
     * Writes each task from the task list into the file via the writeToFile function from storage class.
     *
     * @param ui An instance of the TextUi class.
     * @param tasks An instance of the TaskList class.
     * @param storage An instance of the storage class.
     */
    public void writeToFile(TextUi ui, TaskList tasks, Storage storage) {
        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            ui.showCustomText(ErrorMessages.MESSAGE_ERROR_FILE_IO);
        }
    }

    /**
     * Executes the operations related to the command.
     *
     * @param ui An instance of the TextUi class.
     * @param tasks An instance of the TaskList class.
     * @param storage An instance of the Storage class.
     */
    public abstract void execute(TextUi ui, TaskList tasks, Storage storage);
}
