package duke.exceptions;

public class InvalidCommandException extends Exception{
    private static final String MESSAGE = "Invalid command.";
    public String getMessage() {
        return MESSAGE;
    }
}
