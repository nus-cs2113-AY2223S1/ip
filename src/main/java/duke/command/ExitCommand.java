package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to exit the application
 */
public class ExitCommand extends Command {

    /**
     * Creates an exit command
     * 
     * @param input The user input string
     */
    public ExitCommand(String input) {
        super(input);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage, TaskList lastResults) throws DukeException {
        return taskList;
    }

}
