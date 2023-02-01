package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.exception.InvalidIndexException;
import duke.storage.Storage;
import duke.ui.Ui;

import static duke.common.Constants.MAX_TASK_STRING_LENGTH;

/**
 * <code>ModifyCommand</code> is the abstract base class for the commands that modify a specific requested task.
 * <p>
 * Extended by <code>DeleteCommand</code>, <code>MarkCommand</code> and <code>UnmarkCommand</code>.
 */
public abstract class ModifyCommand extends Command {
    public static String COMMAND_WORD;
    // One-based index of a task in the task list.
    protected int taskIndex;

    /**
     * Constructor for <code>ModifyCommand</code>
     */
    public ModifyCommand() {
        super();
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks the validity of user input and set the one-based task index.
     *
     * @param parameterInput The extracted part of user input after the command entered.
     * @throws DukeException Exception triggered on invalid task index.
     */
    @Override
    public void checkAndSetParameters(String parameterInput) throws DukeException {
        checkParameters(parameterInput);
        setParameters(parameterInput);
    }

    /**
     * Checks that user input contains a valid task index.
     *
     * @param parameterInput The extracted part of user input after the command entered.
     * @throws DukeException Exception triggered on invalid task index.
     */
    @Override
    protected void checkParameters(String parameterInput) throws DukeException {
        checkTaskIndexInputFormat(parameterInput);
    }

    /**
     * Casts the one-based task index into integer and stores it.
     *
     * @param parameterInput The extracted part of user input after the command entered.
     */
    @Override
    protected void setParameters(String parameterInput) {
        taskIndex = Integer.parseInt(parameterInput);
    }

    /**
     * Checks if the user input is a string parsable to integer.
     *
     * @param parameterInput The extracted part of user input after the command entered.
     * @throws DukeException Exception triggered on invalid task index.
     */
    protected void checkTaskIndexInputFormat(String parameterInput) throws DukeException {
        // Check if the string only contains digits
        if (!parameterInput.matches("^\\d+$")) {
            throw new InvalidIndexException();
        }
        // Check that the index will not exceed 1000000000
        if (parameterInput.length() > MAX_TASK_STRING_LENGTH) {
            throw new InvalidIndexException();
        }
    }

    /**
     * Checks if the task index is within the valid range of task list
     *
     * @param taskIndex    One-based task index
     * @param taskListSize The size of the task list
     * @throws DukeException Exception triggered on invalid user input.
     */
    protected void checkTaskIndexRange(int taskIndex, int taskListSize) throws DukeException {
        if (taskIndex <= 0 || taskIndex > taskListSize) {
            // Out-of-bound task index
            throw new InvalidIndexException();
        }
    }
}
