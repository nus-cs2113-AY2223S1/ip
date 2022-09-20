package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.TextUi;

public class UpdateCommand extends Command {
    protected String description;

    public UpdateCommand(String description) {
        this.description = description;
    }

    public int checkIndexIsValid(String description, int taskCount) throws NumberFormatException {
        int taskIndex = Integer.parseInt(description) - 1;
        if (taskIndex < 0 || taskIndex > taskCount) {
            throw new IndexOutOfBoundsException();
        }
        return taskIndex;
    }

    public void execute(TextUi ui, TaskList tasks, Storage storage) {}
}
