package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(String input) {
        super(input);
        index = Integer.parseInt(input.substring("unmark".length()).trim());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.markUndone(index);
        ui.displayMessage(String.format("OK, I've marked this task as not done yet:\n %s", taskList.getItem(index)));

    }

}
