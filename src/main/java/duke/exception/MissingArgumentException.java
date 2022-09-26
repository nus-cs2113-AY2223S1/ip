package duke.exception;

public class MissingArgumentException extends DukeException {

    private final String keyword;

    public MissingArgumentException(String message) {
        super();
        this.keyword = message;
    }

    @Override
    public String getMessage() {
        String partOne = "☹ OOPS!!! Your ";
        String partTwo = " command is missing an argument!";
        return partOne + keyword + partTwo;
    }
}
