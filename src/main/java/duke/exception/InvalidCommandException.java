package duke.exception;

public class InvalidCommandException extends DukeException {

    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.INVALID_COMMAND_ERROR_MESSAGE;
    }
}
