package duke.command;

import java.util.ArrayList;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import duke.util.asset.Task;
import duke.util.asset.Event;
import duke.util.asset.Deadline;
import duke.util.asset.Todo;

public class AddCommand extends Command {
    private final Task task;
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);

        if(storage.isLoaded()) {
            ui.addLine(tasks.getMessages());
            ui.printUi();
        }

        tasks.clearBuffer();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public static ArrayList<String> getHelpMessage() {
        return new ArrayList<>() {
            {
                add(Deadline.MESSAGE_HELP);
                add(Event.MESSAGE_HELP);
                add(Todo.MESSAGE_HELP);
            }
        };
    }

}
