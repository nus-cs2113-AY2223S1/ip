package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;

import static duke.task.TaskList.tryDeleteTask;

public class DeleteCommand extends Command {
    public DeleteCommand(String keyword, String statement) {
        this.keyword = keyword;
        this.statement = statement;
    }
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        tryDeleteTask(tasks, (keyword + " " + statement).split(" "));
    }
}
