package duke.exceptions;

public class UnknownCommandException extends DukeException{
    /**
     * Prints an error message to inform the user that they did not provide a valid command to the program.
     */
    @Override
    public void printErrorMessage() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "    ____________________________________________________________";
        System.out.println(error);
    }
}
