package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.exception.InvalidIndexException;
import duke.storage.Storage;
import duke.ui.Ui;

import static duke.common.Constants.MAX_TASK_STRING_LENGTH;

public abstract class ModifyCommand extends Command {
    public static String COMMAND_WORD;
    protected int taskIndex;

    public ModifyCommand() {
        super();
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    @Override
    public void checkAndSetParameters(String parameterInput) throws DukeException {
        checkParameters(parameterInput);
        setParameters(parameterInput);
    }

    @Override
    protected void checkParameters(String parameterInput) throws DukeException {
        checkTaskIndexInputFormat(parameterInput);
    }

    @Override
    protected void setParameters(String parameterInput) {
        taskIndex = Integer.parseInt(parameterInput);
    }

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

    protected void checkTaskIndexRange(int taskIndex, int taskListSize) throws DukeException {
        if (taskIndex <= 0 || taskIndex > taskListSize) {
            // Out-of-bound task index
            throw new InvalidIndexException();
        }
    }
}
