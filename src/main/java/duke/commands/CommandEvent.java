package duke.commands;

import duke.DukeException;
import duke.TaskList;

/**
 * Represents the command to manage the processing of an event command after it has been recognised by the parser.
 */
public class CommandEvent {
    /**
     * Deconstructs the raw command into key phrases and checks them, then adds a new event to the task list.
     * @param command Command read from user.
     * @param taskList A task manager that contains the list of tasks and other task list related methods.
     * @return Successful add message to the user.
     * @throws DukeException.IllegalEventFormatException If format of the deadline command is incorrect.
     * @throws DukeException.IllegalEventDateException If the indicated date in the command is invalid.
     */
    public static String processNewEvent(String command, TaskList taskList)
            throws DukeException.IllegalEventFormatException, DukeException.IllegalEventDateException {
        int spacePosition = command.indexOf(" ");
        int dividerPosition = command.indexOf("/");

        if(spacePosition < 0 || dividerPosition < 0) {
            throw new DukeException.IllegalEventFormatException();
        } else if (dividerPosition + 2 > command.length()) {
            throw new DukeException.IllegalEventDateException();
        }

        String taskName = command.substring(spacePosition + 1, dividerPosition);
        String at = command.substring(dividerPosition + 1);
        return taskList.addNewEvent(taskName, at, true);
    }
}
