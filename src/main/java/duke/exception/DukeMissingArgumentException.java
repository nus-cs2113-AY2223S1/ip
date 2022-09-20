package duke.exception;

public class DukeMissingArgumentException extends Exception {
    private String arguments;

    public DukeMissingArgumentException(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return "Your arguments: " + arguments + "\n"
                + "Missing arguments";
    }
}

