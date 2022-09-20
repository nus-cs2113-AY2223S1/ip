package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to mark a task as undone.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Creates an Unmark command.
     * 
     * @param input The user input string
     */
    public UnmarkCommand(String input) throws DukeException {
        super(input);
        String indexString = Parser.removeKeyword(input).trim();
        try {
            index = Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            throw new DukeException("Sorry I didn't understand '" + indexString + "' for parameter INDEX");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage, TaskList lastResults) throws DukeException {
        taskList.markUndone(lastResults.getItem(index));
        ui.displayMessage(String.format("OK, I've marked this task as not done yet:\n %s", taskList.getItem(index)));
        return lastResults;
    }

}
