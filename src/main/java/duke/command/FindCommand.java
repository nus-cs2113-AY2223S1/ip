package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;

import static duke.task.TaskList.findTask;

public class FindCommand extends Command{

    String statement;

    public FindCommand(String statement) {
        this.statement = statement;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        findTask(tasks, statement);
    }
}
