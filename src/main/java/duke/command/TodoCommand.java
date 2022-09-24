package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.task.TaskList.tryAddTodo;

public class TodoCommand extends Command{
    private String line;
    public TodoCommand(String line) {
        this.line = line;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tryAddTodo(tasks, line);
    }
}
