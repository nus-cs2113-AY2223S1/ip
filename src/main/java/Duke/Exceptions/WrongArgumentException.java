package Duke.Exceptions;

import Duke.Duke;
import Duke.Exceptions.DukeException;

public class WrongArgumentException extends DukeException {
    public void WrongArguementMessage() {
        System.out.println(
                Duke.PRINT_LINE
                        + "☹ OOPS!!! I'm sorry, but this is not in the list\n"
                        + Duke.PRINT_LINE
        );
    }
}
