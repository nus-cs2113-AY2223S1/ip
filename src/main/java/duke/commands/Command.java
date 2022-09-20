package duke.commands;

import duke.common.ErrorMessages;
import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.TextUi;

import java.io.IOException;

public abstract class Command {

    public Command() {}

    public void writeToFile(TextUi ui, TaskList tasks, Storage storage) {
        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            ui.showCustomText(ErrorMessages.MESSAGE_ERROR_FILE_IO);
        }
    }

    public abstract void execute(TextUi ui, TaskList tasks, Storage storage);
}
