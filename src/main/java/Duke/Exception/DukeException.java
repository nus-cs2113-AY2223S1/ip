package Duke.Exception;

public class DukeException extends Exception {

    private String errorMessage;

    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
