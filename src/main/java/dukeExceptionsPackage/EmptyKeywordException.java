package dukeExceptionsPackage;

public class EmptyKeywordException extends DukeException {
    /**
     * Constructor for exception
     */

    public EmptyKeywordException(String message) {
        super(message);
    }

    /**
     * Message to be returned when this exception is caught
     *
     * @return message string
     */

    @Override
    public String getExceptionMessage() {
        return "Please enter a keyword.";
    }
}
