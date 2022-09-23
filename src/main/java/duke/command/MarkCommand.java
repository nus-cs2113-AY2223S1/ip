package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.parser.Parser;

/**
 * Command to mark a task as done
 */
public class MarkCommand extends Command {
    public static final String KEYWORD = "mark";
    private final int index;
    private static final String SUCCESS_MESSAGE = "Nice! I've marked this task as done:";

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
            throw new DukeException(DukeException.getDidNotUnderstandIndexMessage(indexString));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage, TaskList lastResults) throws DukeException {
        taskList.markDone(lastResults.getItem(index));
        ui.displayMessage(SUCCESS_MESSAGE + "\n " + taskList.getItem(index));
        return lastResults;
    }

}
