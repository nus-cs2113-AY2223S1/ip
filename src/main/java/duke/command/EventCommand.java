package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import static duke.task.TaskList.tryAddEvent;

public class EventCommand extends Command{
    public EventCommand(String keyword, String statement) {
        this.keyword = keyword;
        this.statement = statement;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = taskList.getTasks();
        tryAddEvent(tasks, statement);
        try {
            storage.write(tasks);
        } catch (IOException e) {
            createDirectory(ui, storage, tasks);
        }
    }
}
