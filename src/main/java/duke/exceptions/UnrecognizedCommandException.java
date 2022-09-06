package duke.exceptions;

public class UnrecognizedCommandException extends Exception {
    private final String errorMessage = "That command is unrecognized! Please enter a valid command or 'help' for a list of valid commands!";

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
