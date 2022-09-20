package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeIOException;
import duke.exception.EmptyDescriptionException;
import duke.task.TaskList;

public class AddTodoCommand extends Command {
    private String input;

    public AddTodoCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyDescriptionException, DukeIOException {
        tasks.addTodo(input, ui);
        storage.writeToFile(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
