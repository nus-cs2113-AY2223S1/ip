package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to list out tasks.
 */
public class ListCommand extends Command {
    public static final String KEYWORD = "list";

    /**
     * Creates a List command.
     * 
     * @param input The user input string
     */
    public ListCommand(String input) {
        super(input);
    }

    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage, TaskList lastResults) throws DukeException {
        ui.displayMessage(taskList.toString());
        return taskList;
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
