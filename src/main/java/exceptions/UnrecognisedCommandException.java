package exceptions;

public class UnrecognisedCommandException extends Exception{
    @Override
    public String getMessage() {
        return "Unrecognised command!";
    }
}
