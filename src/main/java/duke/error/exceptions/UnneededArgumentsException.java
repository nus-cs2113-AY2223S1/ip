package duke.error.exceptions;

public class UnneededArgumentsException extends CustomException {
    private final String command;

    public UnneededArgumentsException(String command) {
        super();
        this.command = command;
    }

    @Override
    public String getExceptionMessage() {
        return String.format("There were unrecognized arguments after "
                + "the <%1$s> command. Please try the <%1$s> command"
                + " again by itself.", command);
    }
}
