package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;
import static duke.task.TaskList.tryMarkTask;

public class MarkCommand extends Command {
    public MarkCommand(String keyword, String statement) {
        this.keyword = keyword;
        this.statement = statement;
    }
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        tryMarkTask(tasks, statement);
    }
}
