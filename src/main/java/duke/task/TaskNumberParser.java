package duke.task;

import duke.exception.DukeException;
import duke.exception.InvalidTaskNumberDukeException;

/**
 * Converts user input into task number
 */
public class TaskNumberParser {

    /**
     * Extracts task number from user input
     *
     * @param arguments user String input containing task number
     * @return task number
     * @throws DukeException if input is not int
     */
    public static int extractTaskNumber(String arguments) throws DukeException {
        try {
            return Integer.parseInt(arguments.trim());
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberDukeException();
        }
    }
}