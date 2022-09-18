package duke.error.exceptions;

/**
 * Exception subclass of {@link DukeException} for if no items were found in list after the
 * {@link duke.Duke#COMMAND_FIND} command is used.
 */
public class ItemNotFoundInListException extends DukeException {
    private final String substring;

    /**
     * Constructor for exception
     *
     * @param substring search substring
     */
    public ItemNotFoundInListException(String substring) {
        super();
        this.substring = substring;
    }

    /**
     * Message to be returned depending on exception.
     *
     * @return message string
     */
    @Override
    public String getExceptionMessage() {
        return String.format("No items contain your search string \"%s\". Please try again.", substring);
    }
}
