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
    public static final String KEYWORD = "unmark";
    private final int index;
    private static final String SUCCESS_MESSAGE = "OK, I've marked this task as not done yet:";

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
            throw new DukeException(DukeException.getDidNotUnderstandIndexMessage(indexString));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage, TaskList lastResults) throws DukeException {
        taskList.markUndone(lastResults.getItem(index));
        ui.displayMessage(SUCCESS_MESSAGE + "\n " + taskList.getItem(index));
        return lastResults;
    }

}
