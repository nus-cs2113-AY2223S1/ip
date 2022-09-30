package exceptions;

public class UnrecognisedCommandException extends Exception{
    
    /**
     * Returns error message
     * 
     * @return string representing the error message
     */
    @Override
    public String getMessage() {
        return "Unrecognised command!";
    }
}
