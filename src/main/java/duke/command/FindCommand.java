package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * <code>FindCommand</code> is the command that list all the tasks in the task list
 * that the description of the task matches with the requested query.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "FIND";
    private String query;

    public FindCommand() {
        super();
    }

    /**
     * Stores the query to the user input received.
     *
     * @param parameterInput The extracted part of user input after the command entered.
     * @throws DukeException Unused.
     */
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

    /**
     * Searches through the current task list to receive a temporary task list
     * containing the tasks that the description contains the query requested by user.
     * <p>
     * Then, get all the found tasks from the task lists into a formatted output.
     * Display the received formatted output to the user.
     *
     * @param taskList List of tasks stored in current execution.
     * @param ui       User interface to display messages.
     * @param storage  File storage to read, append or rewrite file.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList tempTaskList = taskList.findTasks(query);
        String listContent = tempTaskList.listTasks();
        ui.displayListingMessage(listContent);
    }
}
