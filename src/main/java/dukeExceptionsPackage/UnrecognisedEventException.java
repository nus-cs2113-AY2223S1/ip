package dukeExceptionsPackage;

import dukeExceptionsPackage.DukeException;

public class UnrecognisedEventException extends DukeException {
    public UnrecognisedEventException(String message) {
        super(message);
    }
    @Override
    public String getExceptionMessage() {
        return "The input for this event is incorrect. Check that you have entered: event <description> /at <date>.";
    }
}
