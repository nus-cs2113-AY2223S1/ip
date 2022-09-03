package duke.error.exceptions;

public class TooManyWordsException extends CustomException {
    private final String command;
    public TooManyWordsException(String command) {
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
        return String.format("The argument after <%s> must be a "
                + "single integer. Please try again", command);
    }
}
