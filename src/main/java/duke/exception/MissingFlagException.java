package duke.exception;

public class MissingFlagException extends DukeException {

    private final String flag;

    public MissingFlagException(String message) {
        super();
        if (message.equals("deadline")) {
            this.flag = "/by";
        } else {
            this.flag = "/at";
        }
    }

    @Override
    public String getMessage() {
        String partOne = "☹ OOPS!!! You did not provide ";
        String partTwo = " in your command.";
        return partOne + flag + partTwo;
    }
}
