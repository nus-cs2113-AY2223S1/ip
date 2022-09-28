package task.command;

import storage.Storage;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.UI;

public class TodoCommand extends Command {
    private Todo todo;

    public TodoCommand(Todo todo) {
        this.todo = todo;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.addTask(todo);
        ui.addMessage(todo, tasks);
        tasks.storeTask(todo, storage);
    }
}
