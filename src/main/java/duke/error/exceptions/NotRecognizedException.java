package duke.error.exceptions;

public class NotRecognizedException extends DukeException {
    private final String input;

    public NotRecognizedException(String input) {
        super();
        this.input = input;
    }

    @Override
    public String getExceptionMessage() {
        return String.format("Sorry! Your input \"%1$s\" wasn't a recognised command. "
                + "Please try again.", input.split(" ")[0]);
    }
}
