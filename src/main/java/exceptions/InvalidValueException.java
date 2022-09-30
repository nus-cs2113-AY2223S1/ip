package exceptions;

public class InvalidValueException extends Exception {
    
    /**
     * Returns error message
     * 
     * @return string representing the error message
     */
    @Override
    public String getMessage() {
        return "Out of bounds value entered!";
    }
}
