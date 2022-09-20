package duke.exception;

public class DukeExtraArgumentException extends Exception {
    private String arguments;

    public DukeExtraArgumentException(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return "EXCEPTION: Extra arguments present\n"
                + "Your arguments: " + arguments + "\n"
                + "Please check the User Guide for the correct arguments";
    }
}
