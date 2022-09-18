package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.task.TaskList;

public class HelpCommand extends Command {

    public HelpCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.output("You asked for help, but I don't feel like helping ;p\n" +
                "Maybe try saying the magic word?");
    }
}
