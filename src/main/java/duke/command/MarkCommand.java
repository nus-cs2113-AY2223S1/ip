package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {

    private final int index;

    public MarkCommand(String input) {
        super(input);
        index = Integer.parseInt(input.substring("mark".length()).trim());
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
