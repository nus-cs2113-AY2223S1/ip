package duke.error.exceptions;

import duke.Duke;

/**
 * Exception subclass of {@link DukeException} for when the {@link Duke#COMMAND_LIST}
 * command is called but list is empty
 */
public class ListEmptyException extends DukeException {
    private final String command;

    /**
     * Constructor for exception
     *
     * @param command command string
     */
    public ListEmptyException(String command) {
        super();
        this.command = command;
    }

    /**
     * Message to be returned depending on exception.
     *
     * @return message string
     */
    @Override
    public String getExceptionMessage() {
        return String.format("Your to-do list is empty. Please add some items "
                + "to your list to use the <%1$s> command.", command);
    }
}
