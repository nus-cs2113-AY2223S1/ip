package duke.error;

import duke.error.exceptions.DukeException;
import duke.ui.UserInterface;

public class ErrorHandler {
    public static void printErrorMessage(DukeException e) {
        UserInterface.print(e.getExceptionMessage());
    }
}
