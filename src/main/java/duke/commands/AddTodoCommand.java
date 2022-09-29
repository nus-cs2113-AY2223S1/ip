package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.tasks.Todo;

/**
 * Represent a command with command word and description specified.
 * There is also a Todo initiated which is used in execute method to be added to task list.
 * Used when command word is 'todo'.
 */
public class AddTodoCommand extends AddCommand {

    protected Todo toAdd;

    public AddTodoCommand(String commandWord, String taskline) {
        super(commandWord, taskline);
        this.toAdd = new Todo(taskline);
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.addTodo(toAdd, storage);
    }
}
