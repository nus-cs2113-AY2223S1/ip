package duke.error.exceptions;

public class NotANumberException extends CustomException {
    private final String command;
    public NotANumberException(String command) {
        super();
        this.command = command;
    }

    /**
     * Message to be used in dialog box
     *
     * @return message string
     */
    @Override
    public String getExceptionMessage() {
        return String.format("The argument after <%s> was not an integer. "
                + "Please try again.", command);
    }
}
