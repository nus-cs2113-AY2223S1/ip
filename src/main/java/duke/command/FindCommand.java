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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.displayMessage(
                "Result of search for: " + searchKeyword + "\n\n" + taskList.filter(searchKeyword).toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
