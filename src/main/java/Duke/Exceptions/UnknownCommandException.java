package Duke.Exceptions;

import Duke.UI.UI;

public class UnknownCommandException extends DukeException {

    /**
     * Prints error message when an invalid command is entered.
     */
    public void UnknownCommandMessage() {
        System.out.println(
                UI.PRINT_LINE
                        + ":( OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                        + UI.PRINT_LINE
        );
    }
}
