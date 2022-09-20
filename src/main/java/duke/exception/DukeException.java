package duke.exception;

public abstract class DukeException extends Exception {

    protected String ERROR_MESSAGE;

    /**
     * Returns error message according to the type of error thrown
     *
     * @return String of error message
     */
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}