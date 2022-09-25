package duke.command;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.exception.DukeException;
import duke.exception.TodoMissingDescriptionException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * <code>TodoCommand</code> is the command thatadds a to-do task into the task list.
 */
public class TodoCommand extends AddCommand {
    public static final String COMMAND_WORD = "TODO";

    public TodoCommand() {
        super();
    }

    /**
     * Check the validity of user input.
     *
     * @param parameterInput The extracted part of user input after the command entered.
     * @throws DukeException Exception triggered on invalid user input.
     */
    @Override
    public void checkAndSetParameters(String parameterInput) throws DukeException {
        checkParameters(parameterInput);
        setParameters(parameterInput);
    }

    /**
     * Check that user input contains a description.
     *
     * @param parameterInput The extracted part of user input after the command entered.
     * @throws DukeException Exception triggered on invalid user input.
     */
    @Override
    protected void checkParameters(String parameterInput) throws DukeException {
        if (!containsTaskDescription(parameterInput, "")) {
            throw new TodoMissingDescriptionException();
        }
    }

    /**
     * Store the task description.
     *
     * @param parameterInput The extracted part of user input after the command entered.
     */
    @Override
    protected void setParameters(String parameterInput) {
        super.taskName = parameterInput;
    }

    /**
     * Create a to-do task and add it to the task list.
     * Display an addition successful message to user.
     * Append the to-do task to the file storage.
     *
     * @param taskList List of tasks stored in current execution.
     * @param ui       User interface to display messages.
     * @param storage  File storage to read, append or rewrite file.
     * @throws DukeException Exception triggered on erroneous file operation.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.addTodo(super.taskName);
        ui.displayTaskAdditionMessage(task.getTaskFullDetails(), taskList.getSize());
        storage.appendDukeFile(taskList);
    }
}
