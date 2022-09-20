package duke.exception;

public class DukeMissingArgumentException extends Exception {
    private String arguments;

    public DukeMissingArgumentException(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return "EXCEPTION: Missing Arguments\n"
                + "Your arguments: " + arguments + "\n"
                + "Please check the User Guide for the correct arguments";
    }
}

