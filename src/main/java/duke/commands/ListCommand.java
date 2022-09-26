package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printLine();
        tasks.listTasks();
        ui.printLine();
    }
}
