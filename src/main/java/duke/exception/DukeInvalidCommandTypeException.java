package duke.exception;

public class DukeInvalidCommandTypeException extends Exception {

    private String commandType;

    public DukeInvalidCommandTypeException(String commandType) {
        this.commandType = commandType;
    }

    @Override
    public String toString() {
        return "EXCEPTION: Invalid command type\n"
                + "Your command: " + commandType + "\n"
                + "Please check the User Guide for the list of valid command types";
    }
}
