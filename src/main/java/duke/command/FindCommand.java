package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to filter tasks in the list.
 */
public class FindCommand extends Command {
    public static final String KEYWORD = "find";
    private String searchKeyword;
    public static final String NO_MATCHES_MESSAGE_HEADER = "No tasks matched your search keyword: ";
    public static final String RESULT_MESSAGE_HEADER = "Result of search for: ";

    /**
     * Creates a Find command.
     * 
     * @param input The user input string
     */
    public FindCommand(String input) {
        super(input);
        searchKeyword = Parser.removeKeyword(input);
    }

    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage, TaskList lastResults) throws DukeException {
        TaskList filtered = taskList.filter(searchKeyword);
        if (filtered.size() == 0) {
            ui.displayMessage(NO_MATCHES_MESSAGE_HEADER + searchKeyword);
            return lastResults;
        } else {
            ui.displayMessage(RESULT_MESSAGE_HEADER + searchKeyword + "\n\n" + filtered.toString());
            return filtered;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
