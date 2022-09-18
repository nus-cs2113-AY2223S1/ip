package duke.command;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.exception.DeadlineMissingTagException;
import duke.exception.DeadlineMissingDescriptionException;
import duke.exception.DeadlineMissingTimeException;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * <code>DeadlineCommand</code> is the command that adds a deadline task into the task list.
 */
public class DeadlineCommand extends AddCommand {
    public static final String COMMAND_WORD = "DEADLINE";
    private String deadlineTime;

    public DeadlineCommand() {
        super();
    }

    /**
     * Check the validity of user input and set the deadline description and time of the deadline.
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
     * Check that user input contains a description, separator and a time.
     *
     * @param parameterInput The extracted part of user input after the command entered.
     * @throws DukeException Exception triggered on invalid user input.
     */
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

    /**
     * Store the deadline description and time of the deadline.
     *
     * @param parameterInput The extracted part of user input after the command entered.
     */
    @Override
    protected void setParameters(String parameterInput) {
        String[] splits = splitTaskName(" /by ", parameterInput);
        super.taskName = splits[0];
        deadlineTime = splits[1];
    }

    /**
     * Create a deadline task and add it to the task list.
     * Display an addition successful message to user.
     * Append the deadline task to the file storage.
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
