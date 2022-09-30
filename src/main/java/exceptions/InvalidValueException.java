package exceptions;

public class InvalidValueException extends Exception {
    @Override
    public String getMessage() {
        return "Out of bounds value entered!";
    }
}
