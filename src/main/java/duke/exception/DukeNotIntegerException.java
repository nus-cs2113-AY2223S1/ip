package duke.exception;

public class DukeNotIntegerException extends Exception {

    private String nonInteger;

    /**
     * Constructor
     * @param nonInteger String from the user input that could not be parsed into an integer
     */
    public DukeNotIntegerException(String nonInteger) {
        this.nonInteger = nonInteger;
    }

    /**
     * Get the exception message
     * @return String Exception message
     */
    @Override
    public String toString() {
        return "EXCEPTION: Non-integer input where an integer is expected\n"
                + "Non-integer input: " + nonInteger + "\n"
                + "Please enter an integer";
    }
}
