package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;

import static duke.task.TaskList.printTaskList;

public class ListCommand extends Command{
    public ListCommand(String keyword, String statement) {
        this.keyword = keyword;
        this.statement = statement;
    }

    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        printTaskList(tasks);
    }
}
