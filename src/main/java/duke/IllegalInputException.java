package duke;

/**
 * Class to represent exceptions to do with illegal user inputs.
 */
public class IllegalInputException extends Exception{

    private String errorMessage;

    /**
     * Constructs an exception with custom error messages.
     *
     * @param errorMessage Custom error message.
     */
    public IllegalInputException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
