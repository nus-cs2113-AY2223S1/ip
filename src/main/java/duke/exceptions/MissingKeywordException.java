package duke.exceptions;

public class MissingKeywordException extends DukeException {

    /**
     * Prints an error message to inform the user that they did not provide a keyword.
     */
    @Override
    public void printErrorMessage() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! You did not specify a keyword.\n" +
                "    ____________________________________________________________";
        System.out.println(error);
    }
}
