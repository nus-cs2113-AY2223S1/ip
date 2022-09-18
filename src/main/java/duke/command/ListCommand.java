package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * <code>ListCommand</code> is the command that list all the tasks in the task list.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "LIST";

    public ListCommand() {
        super();
    }

    @Override
    public void checkAndSetParameters(String parameterInput) throws DukeException {
    }

    @Override
    protected void checkParameters(String parameterInput) throws DukeException {
    }

    @Override
    protected void setParameters(String parameterInput) {
    }

    /**
     * Get all the tasks from the task lists in a formatted output.
     * Display the received formatted output to the user.
     *
     * @param taskList List of tasks stored in current execution.
     * @param ui       User interface to display messages.
     * @param storage  File storage to read, append or rewrite file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String listContent = taskList.listTasks();
        ui.displayListingMessage(listContent);
    }
}
