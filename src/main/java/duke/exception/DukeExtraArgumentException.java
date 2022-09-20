package duke.exception;

public class DukeExtraArgumentException extends Exception {
    private String arguments;

    public DukeExtraArgumentException(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return "Your arguments: " + arguments + "\n"
                + "Extra arguments present";
    }
}
