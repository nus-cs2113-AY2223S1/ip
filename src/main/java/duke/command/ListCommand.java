package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

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
