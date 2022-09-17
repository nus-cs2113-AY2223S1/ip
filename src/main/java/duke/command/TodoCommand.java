package duke.command;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.exception.DukeException;
import duke.exception.TodoMissingDescriptionException;
import duke.storage.Storage;
import duke.ui.Ui;

public class TodoCommand extends AddCommand {
    public static final String COMMAND_WORD = "TODO";

    public TodoCommand() {
        super();
    }

    @Override
    public void checkAndSetParameters(String parameterInput) throws DukeException {
        checkParameters(parameterInput);
        setParameters(parameterInput);
    }

    @Override
    protected void checkParameters(String parameterInput) throws DukeException {
        if (!containsTaskDescription(parameterInput, "")) {
            throw new TodoMissingDescriptionException();
        }
    }

    @Override
    protected void setParameters(String parameterInput) {
        super.taskName = parameterInput;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.addTodo(super.taskName);
        ui.displayTaskAdditionMessage(task.getTaskFullDetails(), taskList.getSize());
        storage.appendDukeFile(taskList);
    }
}
