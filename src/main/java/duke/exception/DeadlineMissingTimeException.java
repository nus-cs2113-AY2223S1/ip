package duke.exception;

public class DeadlineMissingTimeException extends DukeException {

    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.DEADLINE_MISSING_TIME_ERROR_MESSAGE + super.getMessagePostfix();
    }
}
