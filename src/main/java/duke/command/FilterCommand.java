package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.parser.DukeDateParser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.time.LocalDate;

/**
 * <code>FilterCommand</code> is the command that list all the deadline and event tasks in the task list
 * that the date of the task matches with the requested date.
 */
public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "FILTER";
    private LocalDate targetDate;
    private DukeDateParser parser;

    /**
     * Constructor of <code>FilterCommand</code>, creates a DukeDateParser class
     * used to parse the user input into a valid LocalDate
     */
    public FilterCommand() {
        super();
        parser = new DukeDateParser();
    }

    /**
     * Check the validity of user input and parse it into LocalDate class.
     * Then store the targeted date to filter for.
     *
     * @param parameterInput The extracted part of user input after the command entered.
     * @throws DukeException Exception triggered on invalid LocalDate format.
     */
    @Override
    public void checkAndSetParameters(String parameterInput) throws DukeException {
        targetDate = parser.parse(parameterInput);
    }

    @Override
    protected void checkParameters(String parameterInput) {
    }

    @Override
    protected void setParameters(String parameterInput) throws DukeException {
    }

    /**
     * Filter the current task list to receive a temporary task list
     * containing the tasks that the date matches with the date requested by user.
     * <p>
     * Then, get all the filtered tasks from the task lists into a formatted output.
     * Display the received formatted output to the user.
     *
     * @param taskList List of tasks stored in current execution.
     * @param ui       User interface to display messages.
     * @param storage  File storage to read, append or rewrite file.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList tempTaskList = taskList.filterTasks(targetDate);
        String listContent = tempTaskList.listTasks();
        ui.displayListingMessage(listContent);
    }
}
