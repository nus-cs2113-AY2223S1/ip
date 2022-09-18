package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "LIST";

    public ListCommand() {
        super();
    }

    @Override
    public void checkAndSetParameters(String parameterInput) {
    }

    @Override
    protected void checkParameters(String parameterInput) {
    }

    @Override
    protected void setParameters(String parameterInput) {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String listContent = taskList.listTasks();
        ui.displayListingMessage(listContent);
    }
}
