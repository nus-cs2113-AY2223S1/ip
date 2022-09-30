package duke.commands;

import duke.data.TaskList;
import duke.data.exception.TodoNoTitleException;
import duke.storage.Storage;
import duke.ui.TextUi;

/**
 * Represents a task command object that will be inherited by deadline, event and todo command objects.
 */
public class AddCommand extends Command {
    protected String description;
    protected boolean isDone;

    /**
     * Initialises the variables of the AddCommand class.
     *
     * @param description A string that represents the title, deadline and/or time of the task.
     * @param isDone A boolean that indicates whether the task is done or undone.
     */
    public AddCommand(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Checks the format of todo task to ensure that it has a task title.
     *
     * @param title A string that represents the title of the task.
     * @throws TodoNoTitleException If todo task does not contain a title.
     */
    public void checkTodoFormat(String title) throws TodoNoTitleException {
        if (description.isBlank()) {
            throw new TodoNoTitleException();
        }
    }

    /**
     * Executes the operations related to the command.
     *
     * @param ui An instance of the TextUi class.
     * @param tasks An instance of the TaskList class.
     * @param storage An instance of the Storage class.
     */
    public void execute(TextUi ui, TaskList tasks, Storage storage) {}
}
