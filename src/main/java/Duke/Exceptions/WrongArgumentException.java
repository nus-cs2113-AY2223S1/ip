package Duke.Exceptions;

import Duke.UI.UI;

public class WrongArgumentException extends DukeException {

    /**
     * Prints error message when an invalid task number is entered.
     */
    public void WrongArguementMessage() {
        System.out.println(
                UI.PRINT_LINE
                        + ":( OOPS!!! I'm sorry, but this is not in the list\n"
                        + UI.PRINT_LINE
        );
    }
}
