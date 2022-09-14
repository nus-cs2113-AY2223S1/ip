package Duke.Exceptions;

public class EmptyArgumentException extends Exception {
    private static final String ERROR_MESSAGE = "Please provide a correct command!";

    public EmptyArgumentException() {
        super(ERROR_MESSAGE);
    }

    @Override
    public String toString() {
        return ERROR_MESSAGE;
    }
}