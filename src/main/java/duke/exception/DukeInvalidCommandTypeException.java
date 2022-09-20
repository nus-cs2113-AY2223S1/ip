package duke.exception;

public class DukeInvalidCommandTypeException extends Exception {

    private String commandType;

    /**
     * Constructor
     * @param commandType Command type
     */
    public DukeInvalidCommandTypeException(String commandType) {
        this.commandType = commandType;
    }

    /**
     * Get the exception message
     * @return String Exception message
     */
    @Override
    public String toString() {
        return "EXCEPTION: Invalid command type\n"
                + "Your command: " + commandType + "\n"
                + "Please check the User Guide for the list of valid command types";
    }
}
