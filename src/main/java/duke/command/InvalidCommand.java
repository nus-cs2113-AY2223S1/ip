package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.task.TaskList;

public class InvalidCommand extends Command{

    public InvalidCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.output("Invalid input. Boo! Please type help for help");
    }
}
