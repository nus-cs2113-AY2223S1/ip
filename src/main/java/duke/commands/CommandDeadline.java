package duke.commands;

import duke.DukeException;
import duke.TaskList;

/**
 * Represents the command to manage the processing of a deadline command after it has been recognised by the parser.
 */
public class CommandDeadline extends Command {
    /**
     * Deconstructs the raw command into key phrases and checks them, then adds a new deadline to the task list.
     *
     * @param command Command read from user.
     * @param taskList A task manager that contains the list of tasks and other task list related methods.
     * @return Successful add message to the user.
     * @throws DukeException.IllegalDeadlineFormatException If format of the deadline command is incorrect.
     * @throws DukeException.IllegalDeadlineDateException If the indicated date in the command is invalid.
     */
    public static String processNewDeadline(String command, TaskList taskList)
            throws DukeException.IllegalDeadlineFormatException, DukeException.IllegalDeadlineDateException {
        int spacePosition = command.indexOf(" ");
        int dividerPosition = command.indexOf("/");

        if(spacePosition < 0 || dividerPosition < 0) {
            throw new DukeException.IllegalDeadlineFormatException();
        } else if (dividerPosition + 2 > command.length()) {
            throw new DukeException.IllegalDeadlineDateException();
        }

        String taskName = command.substring(spacePosition + 1, dividerPosition);
        String by = command.substring(dividerPosition + 1);

        try {
            checkDateTime(by);
        } catch (DukeException.IllegalDateTimeException e) {
            throw new DukeException.IllegalDeadlineDateException();
        }

        return taskList.addNewDeadline(taskName, by, true);
    }
}
