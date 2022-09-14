package duke.exception;

public class MissingIntegerException extends DukeException {

    private final String keyword;

    public MissingIntegerException(String message) {
        super();
        this.keyword = message;
    }

    @Override
    public String getMessage() {
        String partOne = "☹ OOPS!!! Your ";
        String partTwo = " command is missing an integer to indicate which task.";
        return partOne + keyword + partTwo;
    }
}
