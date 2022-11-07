package dukeExceptionsPackage;

public class EmptyListException extends DukeException {
    public EmptyListException(String message) {
        super(message);
    }
    @Override
    public String getExceptionMessage() {
        return "The list is empty. There is nothing to show :<";
    }
}
