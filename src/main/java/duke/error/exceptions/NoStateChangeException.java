package duke.error.exceptions;

public class NoStateChangeException extends DukeException {
    boolean isDone;
    public NoStateChangeException(boolean isDone) {
        super();
        this.isDone = isDone;
    }

    /**
     * Message to be used in dialog box
     *
     * @return message string
     */
    @Override
    public String getExceptionMessage() {
        return "Item is already " + (isDone ? "marked." : "unmarked.") + " Did you mean to type "
                + (isDone ? "<unmark> ?" : "<mark> ?") + "\nPlease try again.";
    }
}
