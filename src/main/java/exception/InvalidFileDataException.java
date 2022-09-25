package exception;

/**
 * Exception if data found in file is invalid or text file is invalid
 */
public class InvalidFileDataException extends Exception {
    private String MESSAGE = "Invalid file data detected. File data will not be read.";
    /**
     * Returns corresponding error message when called
     */
    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
