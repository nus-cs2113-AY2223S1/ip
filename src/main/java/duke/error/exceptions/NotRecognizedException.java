package duke.error.exceptions;

/**
 * Exception subclass of {@link DukeException} for if a given string does not have
 * a recognized command.
 */
public class NotRecognizedException extends DukeException {
    private final String input;

    /**
     * Constructor for exception
     *
     * @param input input string
     */
    public NotRecognizedException(String input) {
        super();
        this.input = input;
    }

    /**
     * Message to be returned depending on exception.
     *
     * @return message string
     */
    @Override
    public String getExceptionMessage() {
        return String.format("Sorry! Your input \"%1$s\" wasn't a recognised command. "
                + "Please try again.", input.split(" ")[0]);
    }
}
