package duke.error.exceptions;

/**
 * Exception subclass of {@link DukeException} for if an item's desired state and current state match.
 * E.g. If an item is marked and the user tries to call the
 * {@link duke.Duke#COMMAND_MARK Duke.COMMAND_MARK} command.
 */
public class NoStateChangeException extends DukeException {
    boolean isDone;

    /**
     * Constructor for exception
     *
     * @param isDone boolean state of item
     */
    public NoStateChangeException(boolean isDone) {
        super();
        this.isDone = isDone;
    }

    /**
     * Message to be returned depending on exception.
     *
     * @return message string
     */
    @Override
    public String getExceptionMessage() {
        return "Item is already " + (isDone ? "marked." : "unmarked.") + " Did you mean to type "
                + (isDone ? "<unmark> ?" : "<mark> ?") + "\nPlease try again.";
    }
}
