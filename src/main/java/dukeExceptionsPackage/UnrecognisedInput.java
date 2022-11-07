package dukeExceptionsPackage;

import dukeExceptionsPackage.DukeException;

/**
 * Exception subclass of {@DukeException} when an input is not recognised by duke.Duke
 *
 *
 */
public class UnrecognisedInput extends DukeException {


    /**
     * Constructor for exception
     */

    public UnrecognisedInput(String message) {
        super(message);
    }

    /**
     * Message to be returned when this exception is caught
     *
     * @return message string
     */

    @Override
    public String getExceptionMessage() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

}
