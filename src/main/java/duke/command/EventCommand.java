package duke.command;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.exception.DukeException;
import duke.exception.EventMissingDescriptionException;
import duke.exception.EventMissingTagException;
import duke.exception.EventMissingTimeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class EventCommand extends AddCommand {
    public static final String COMMAND_WORD = "EVENT";
    private String eventTime;

    public EventCommand() {
        super();
    }

    @Override
    public void checkAndSetParameters(String parameterInput) throws DukeException {
        checkParameters(parameterInput);
        setParameters(parameterInput);
    }

    @Override
    protected void checkParameters(String parameterInput) throws DukeException {
        if (!containsTimeSeparator(parameterInput, " /at ")) {
            throw new EventMissingTagException();
        }
        if (!containsTaskDescription(parameterInput, " /at ")) {
            throw new EventMissingDescriptionException();
        }
        if (!containsTaskTime(parameterInput, " /at ")) {
            throw new EventMissingTimeException();
        }
    }

    @Override
    protected void setParameters(String parameterInput) {
        String[] splits = splitTaskName(" /at ", parameterInput);
        super.taskName = splits[0];
        eventTime = splits[1];
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.addEvent(super.taskName, eventTime);
        ui.displayTaskAdditionMessage(task.getTaskFullDetails(), taskList.getSize());
        storage.appendDukeFile(taskList);
    }
}
