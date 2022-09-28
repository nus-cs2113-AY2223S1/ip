package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;


public class ByeCommand extends Command {

    public ByeCommand() {
        isExit = true;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = taskList.getTasks();
        System.out.println("     Bye. Hope to see you again soon!");
        try {
            storage.write(tasks);
        } catch (IOException e) {
            createDirectory(ui, storage, tasks);
        }
    }
}
