package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeIOException;
import duke.task.TaskList;

public class AddTodoCommand extends Command {
    private String input;

    /**
     * Constructor of AddTodoCommand Class
     *
     * @param input Input from User
     */
    public AddTodoCommand(String input) {
        this.input = input;
    }

    /**
     * Adds a Todo Task to Tasks and saves Tasks to Storage
     *
     * @param tasks TaskList of tasks
     * @param ui User Interface
     * @param storage Storage to load and save tasks
     * @throws DukeIOException Exception when writeToFile has exceptions
     * @throws DukeEmptyDescriptionException Exception when no description for the deadline was added
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeEmptyDescriptionException, DukeIOException {
        tasks.addTodo(input, ui);
        storage.writeToFile(tasks);
    }

    /**
     * Returns false as command is not ByeCommand
     *
     * @return boolean false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
