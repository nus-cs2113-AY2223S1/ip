package duke.commands;

import duke.DukeException;
import duke.TaskList;

import java.time.format.DateTimeParseException;

public class CommandCheckout {
    private static final String END_OF_LINE = "____________________";
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
