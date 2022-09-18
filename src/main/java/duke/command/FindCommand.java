package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    private String searchKeyword;

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
