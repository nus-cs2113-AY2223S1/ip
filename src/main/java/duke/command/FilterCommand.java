package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.parser.DukeDateParser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.time.LocalDate;

public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "FILTER";
    private LocalDate targetDate;
    private DukeDateParser parser;

    public FilterCommand() {
        super();
        parser = new DukeDateParser();
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
    protected void setParameters(String parameterInput) throws DukeException {
        targetDate = parser.parse(parameterInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList tempTaskList = taskList.filterTasks(targetDate);
        String listContent = tempTaskList.listTasks();
        ui.displayListingMessage(listContent);
    }
}
