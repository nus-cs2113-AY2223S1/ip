package duke.exception;

/**
 * <code>StorageReadException</code> is thrown when an error occurred during the loading
 * of stored data into duke, either in the keyword part or the boolean part.
 */
public class StorageReadException extends DukeException {

    private String MESSAGE_FIRST_PART = "Sorry, there was an error loading the ";
    private String MESSAGE_LAST_PART = " portion of the stored data :-(";
    private String type;

    /**
     * Constructor of <code>StorageReadException</code>.
     * Stores the part of the loading that experienced this error, either from
     * reading the keyword part or the boolean part.
     *
     * @param type the part of the loading that experienced this error
     */
    public StorageReadException(String type) {
        super();
        this.type = type;
    }

    /**
     * Returns the error message of the exception for printing.
     *
     * @return a string that is the error message
     */
    @Override
    public String getMessage() {
        return MESSAGE_FIRST_PART + type + MESSAGE_LAST_PART;
    }
}
