package Duke.Exceptions;

import Duke.UI.UI;

public class ArguementNotFoundException extends DukeException {

    /**
     * Prints error message when task description is empty in user input.
     */
    public void ArgumentNotFoundMessage() {
        System.out.println(
                UI.PRINT_LINE
                        + ":( OOPS!!! The description is empty.\n"
                        + UI.PRINT_LINE
        );
    }

}
