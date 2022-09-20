package duke.exception;

public class DukeExtraArgumentException extends Exception {
    private String arguments;

    /**
     * Constructor
     * @param arguments Portion of user input which contains all the arguments
     */
    public DukeExtraArgumentException(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Get the exception message
     * @return String Exception message
     */
    @Override
    public String toString() {
        return "EXCEPTION: Extra arguments present\n"
                + "Your arguments: " + arguments + "\n"
                + "Please check the User Guide for the correct arguments";
    }
}
