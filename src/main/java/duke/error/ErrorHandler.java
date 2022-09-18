package duke.error;

import duke.error.exceptions.DukeException;
import duke.ui.Ui;

/**
 * Handles custom exceptions (of type {@link DukeException}) and prints error messages.
 */
public class ErrorHandler {
    /**
     * Prints error message
     *
     * @param e Exception of type {@link DukeException}
     */
    public static void printErrorMessage(DukeException e) {
        Ui.print(e.getExceptionMessage());
    }
}
