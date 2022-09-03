package duke.error.exceptions;

public class NoCommandArgumentException extends DukeException {
    private final String command;

    public NoCommandArgumentException(String command) {
        super();
        this.command = command;
    }

    @Override
    public String getExceptionMessage() {
        return String.format("The command <%s> requires an argument after. "
                + "Please try again with an argument after the command.", command);
    }
}
