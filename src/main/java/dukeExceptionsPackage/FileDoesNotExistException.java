package dukeExceptionsPackage;
import dukeExceptionsPackage.DukeException;

public class FileDoesNotExistException extends DukeException {
    /**
     * Constructor for exception
     */

    public FileDoesNotExistException(String message) {
        super(message);
    }

    /**
     * Message to be returned when this exception is caught
     *
     * @return message string
     */

    @Override
    public String getExceptionMessage() {
        return "The file does not exist!";
    }

}

