package duke.error.exceptions;

import duke.io.FileManager;

/**
 * Exception subclass of {@link DukeException} for if there is an issue saving.
 */
public class CouldNotSaveException extends DukeException {

    /**
     * Message to be returned depending on exception.
     *
     * @return message string
     */
    @Override
    public String getExceptionMessage() {
        return "An error occurred while attempting to save. Please try to clear the path \""
                + FileManager.getPath() + "\".";
    }
}
