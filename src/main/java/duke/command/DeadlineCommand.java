package duke.command;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.exception.DeadlineMissingTagException;
import duke.exception.DeadlineMissingDescriptionException;
import duke.exception.DeadlineMissingTimeException;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

import java.time.LocalDateTime;

public class DeadlineCommand extends AddCommand {
    public static final String COMMAND_WORD = "DEADLINE";
    private LocalDateTime deadlineTime;

    public DeadlineCommand() {
        super();
    }

    @Override
    public void checkAndSetParameters(String parameterInput) throws DukeException {
        checkParameters(parameterInput);
        setParameters(parameterInput);
    }

    @Override
    protected void checkParameters(String parameterInput) throws DukeException {
        if (!containsTimeSeparator(parameterInput, " /by ")) {
            throw new DeadlineMissingTagException();
        }
        if (!containsTaskDescription(parameterInput, " /by ")) {
            throw new DeadlineMissingDescriptionException();
        }
        if (!containsTaskTime(parameterInput, " /by ")) {
            throw new DeadlineMissingTimeException();
        }
    }

    @Override
    protected void setParameters(String parameterInput) throws DukeException {
        String[] splits = splitTaskName(" /by ", parameterInput);
        super.taskName = splits[0];
        deadlineTime = convertStringToLocalDateTime(splits[1]);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.addDeadline(super.taskName, deadlineTime);
        ui.displayTaskAdditionMessage(task.getTaskFullDetails(), taskList.getSize());
        storage.appendDukeFile(taskList);
    }
}
