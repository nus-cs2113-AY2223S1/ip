package dukeExceptionsPackage;

public abstract class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
    public abstract String getExceptionMessage();
}
