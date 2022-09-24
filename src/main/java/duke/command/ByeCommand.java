package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;


public class ByeCommand extends Command {

    public ByeCommand() {
        isExit = true;
    }

    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        System.out.println("     Bye. Hope to see you again soon!");
    }
}
