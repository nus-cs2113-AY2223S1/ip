package dukeExceptionsPackage;

import dukeExceptionsPackage.DukeException;

public class EmptyDescriptionException extends DukeException {
    /**
     * Constructor for exception
     */

    public EmptyDescriptionException(String message) {
        super(message);
    }

    /**
     * Message to be returned when this exception is caught
     *
     * @return message string
     */

    @Override
    public String getExceptionMessage() {
        return "☹ The description cannot be empty! Please try again.";
    }

}

