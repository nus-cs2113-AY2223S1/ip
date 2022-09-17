package duke.exception;

public abstract class DukeException extends Exception {
    public abstract String getMessage();

    public String getMessagePrefix() {
        return " ☹ OOPS!!! ";
    }

    public String getMessagePostfix() {
        return System.lineSeparator() + ErrorMessage.BANNER;
    }
}
