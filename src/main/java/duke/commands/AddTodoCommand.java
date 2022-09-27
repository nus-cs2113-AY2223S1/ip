package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.tasks.Todo;

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
