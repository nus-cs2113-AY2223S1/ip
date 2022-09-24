package Duke.Exceptions;

import Duke.Duke;

public class WrongArgumentException extends DukeException {
    public void WrongArguementMessage() {
        System.out.println(
                Duke.PRINT_LINE
                        + "☹ OOPS!!! I'm sorry, but this is not in the list\n"
                        + Duke.PRINT_LINE
        );
    }
}
