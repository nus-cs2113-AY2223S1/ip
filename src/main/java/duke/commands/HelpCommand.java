package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class HelpCommand extends Command {

    public HelpCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printLine();
        ui.printHelpMessage();
        ui.printLine();
    }
}
