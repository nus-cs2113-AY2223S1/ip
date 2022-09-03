package duke.exception;

public abstract class DukeException extends Exception {
    public abstract String getMessage();

    public String getMessagePrefix() {
        return ErrorMessage.BANNER + System.lineSeparator() + " ☹ OOPS!!! ";
    }

    public String getMessagePostfix() {
        return System.lineSeparator() + ErrorMessage.BANNER;
    }
}
