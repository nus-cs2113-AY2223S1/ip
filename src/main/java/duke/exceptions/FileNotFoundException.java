package duke.exceptions;

public class FileNotFoundException extends Exception{
    private static final String MESSAGE = "File not found.";

    public String getMessage() {
        return MESSAGE;
    }
}
