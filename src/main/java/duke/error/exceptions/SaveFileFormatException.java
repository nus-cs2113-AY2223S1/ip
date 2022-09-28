package duke.error.exceptions;

/**
 * Exception subclass of {@link DukeException} for if an item is not found in list at a given index.
 */
public class SaveFileFormatException extends DukeException {

    /**
     * Message to be returned depending on exception.
     *
     * @return message string
     */
    @Override
    public String getExceptionMessage() {
        return "The save file appears to be corrupted. The file will be deleted and re-instanced.";
    }
}
