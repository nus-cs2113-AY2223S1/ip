package duke.exception;

public class DukeMissingDescriptionException extends Exception {

    /**
     * Get the exception message
     * @return String Exception message
     */
    @Override
    public String toString() {
        return "EXCEPTION: Missing Description\n"
                + "Please type in a task description";
    }
}
