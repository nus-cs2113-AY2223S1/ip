package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to mark a task as done
 */
public class MarkCommand extends Command {

    private final int index;

    /**
     * Creates a Mark command
     * 
     * @param input The user input string
     */
    public MarkCommand(String input) throws DukeException {
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
        taskList.markDone(lastResults.getItem(index));
        ui.displayMessage(String.format("Nice! I've marked this task as done:\n %s", taskList.getItem(index)));
        return lastResults;
    }

}
