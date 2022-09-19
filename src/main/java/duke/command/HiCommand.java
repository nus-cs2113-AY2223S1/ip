package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.task.TaskList;

public class HiCommand extends Command {

    public HiCommand() {
        super();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.output("Howdy!");
    }

}
