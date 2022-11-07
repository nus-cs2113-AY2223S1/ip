package duke.commands;

import duke.DukeException;
import duke.TaskList;

import java.time.format.DateTimeParseException;

/**
 * Represents the command to manage the processing of a checkout command after it has been recognised by the parser.
 */
public class CommandCheckout {
    private static final String END_OF_LINE = "____________________";

    /**
     * Checks the command for a date to search, then searches for events and deadlines in the task list.
     *
     * @param command Command read from user.
     * @param taskList A task manager that contains the list of tasks and other task list related methods.
     * @return A list of tasks of that date if any exists.
     * @throws DukeException.IllegalDateTimeException If no valid date is given.
     */
    public static String processCheckout(String command, TaskList taskList)
            throws DukeException.IllegalDateTimeException {
        String[] arrOfCommand = command.split(" ");

        if (arrOfCommand.length != 2) {
            throw new DukeException.IllegalDateTimeException();
        }

        try {
            return taskList.checkoutDate(arrOfCommand[1]) + END_OF_LINE;
        } catch (DateTimeParseException e) {
            throw new DukeException.IllegalDateTimeException();
        }
    }
}
