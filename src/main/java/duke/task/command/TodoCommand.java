package duke.task.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.UI;

/**
 * A class that represents the todo command.
 */
public class TodoCommand extends Command {
    private Todo todo;

    /**
     * Constructor for TodoCommand.
     *
     * @param todo A Todo object.
     */
    public TodoCommand(Todo todo) {
        this.todo = todo;
    }

    /**
     * Executes this todo command by adding a task todo into the list
     * and storing it in the local storage.
     * Displays a message telling user that the task has been added.
     *
     * @param ui Object that handles all user interaction.
     * @param tasks Object that handles and tracks all tasks that the user has added.
     * @param storage Object that handles saving the user's task into the local storage.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.addTask(todo);
        ui.addMessage(todo, tasks);
        storage.storeTask(todo, storage);
    }
}
