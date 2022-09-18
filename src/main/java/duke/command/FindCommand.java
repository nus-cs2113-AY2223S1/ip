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

    private String searchKeyword;

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
            ui.displayMessage("No tasks matched your search keyword: " + searchKeyword);
            return lastResults;
        } else {
            ui.displayMessage("Result of search for: " + searchKeyword + "\n\n" + filtered.toString());
            return filtered;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
