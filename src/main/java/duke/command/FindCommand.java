package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "FIND";
    private String query;

    public FindCommand() {
        super();
    }

    @Override
    public void checkAndSetParameters(String parameterInput) throws DukeException {
        checkParameters(parameterInput);
        setParameters(parameterInput);
    }

    @Override
    protected void checkParameters(String parameterInput) {
    }

    @Override
    protected void setParameters(String parameterInput) {
        query = parameterInput;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList tempTaskList = taskList.findTasks(query);
        String listContent = tempTaskList.listTasks();
        ui.displayListingMessage(listContent);
    }
}
