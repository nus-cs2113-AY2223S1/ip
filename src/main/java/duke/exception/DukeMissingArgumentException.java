package duke.exception;

public class DukeMissingArgumentException extends Exception {
    private String arguments;

    /**
     * Constructor
     * @param arguments Portion of the user input that contains the arguments
     */
    public DukeMissingArgumentException(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Get the exception message
     * @return String Exception message
     */
    @Override
    public String toString() {
        return "EXCEPTION: Missing Arguments\n"
                + "Your arguments: " + arguments + "\n"
                + "Please check the User Guide for the correct arguments";
    }
}

