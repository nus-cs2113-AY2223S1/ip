package exception;

public class InvalidFileDataException extends Exception {
    private String MESSAGE = "Invalid file data detected. File data will not be read.";
    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
