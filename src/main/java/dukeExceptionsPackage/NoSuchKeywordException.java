package dukeExceptionsPackage;

/**
 * Exception subclass of {@DukeException} when an input is not recognised by duke.Duke
 *
 *
 */
public class NoSuchKeywordException extends DukeException {
    private static final String MESSAGE_NO_RESULTS = "Oops, nothing seems to match your search! Try again.";

    /**
     * Constructor for exception
     */

    public NoSuchKeywordException(String message) {
        super(message);
    }

    /**
     * Message to be returned when this exception is caught
     *
     * @return message string
     */

    @Override
    public String getExceptionMessage() {
        return MESSAGE_NO_RESULTS;
    }

}
