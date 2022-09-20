package duke.exception;

public class DukeNotIntegerException extends Exception {

    private String nonInteger;

    public DukeNotIntegerException(String nonInteger) {
        this.nonInteger = nonInteger;
    }

    @Override
    public String toString() {
        return "EXCEPTION: Non-integer input where an integer is expected\n"
                + "Non-integer input: " + nonInteger + "\n"
                + "Please enter an integer";
    }
}
