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

/**
 * <code>DeadlineCommand</code> is the command that adds a deadline task into the task list.
 */
public class DeadlineCommand extends AddCommand {
    public static final String COMMAND_WORD = "DEADLINE";
    public static final String TIME_SEPARATOR = " /by ";
    private LocalDateTime deadlineTime;

    public DeadlineCommand() {
        super();
    }

    /**
     * Checks the validity of user input and set the deadline description and time of the deadline.
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
     * Checks that user input contains a description, separator and a time.
     *
     * @param parameterInput The extracted part of user input after the command entered.
     * @throws DukeException Exception triggered on invalid user input.
     */
    @Override
    protected void checkParameters(String parameterInput) throws DukeException {
        if (!containsTimeSeparator(parameterInput, TIME_SEPARATOR)) {
            throw new DeadlineMissingTagException();
        }
        if (!containsTaskDescription(parameterInput, TIME_SEPARATOR)) {
            throw new DeadlineMissingDescriptionException();
        }
        if (!containsTaskTime(parameterInput, TIME_SEPARATOR)) {
            throw new DeadlineMissingTimeException();
        }
    }

    /**
     * Stores the deadline description and time of the deadline.
     *
     * @param parameterInput The extracted part of user input after the command entered.
     */
    @Override
    protected void setParameters(String parameterInput) throws DukeException {
        String[] splits = splitTaskName(TIME_SEPARATOR, parameterInput);
        super.taskName = splits[0];
        deadlineTime = convertStringToLocalDateTime(splits[1]);
    }

    /**
     * Creates a deadline task and add it to the task list.
     * Displays an addition successful message to user.
     * Appends the deadline task to the file storage.
     *
     * @param taskList List of tasks stored in current execution.
     * @param ui       User interface to display messages.
     * @param storage  File storage to read, append or rewrite file.
     * @throws DukeException Exception triggered on erroneous file operation.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.addDeadline(super.taskName, deadlineTime);
        ui.displayTaskAdditionMessage(task.getTaskFullDetails(), taskList.getSize());
        storage.appendDukeFile(taskList);
    }
}
