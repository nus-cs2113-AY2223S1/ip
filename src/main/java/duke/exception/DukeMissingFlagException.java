package duke.exception;

public class DukeMissingFlagException extends Exception {

    private String missingFlag;

    /**
     * Constructor
     * @param missingFlag The missing flag that triggered the exception
     */
    public DukeMissingFlagException(String missingFlag) {
        this.missingFlag = missingFlag;
    }

    /**
     * Get the exception message
     * @return String Exception message
     */
    @Override
    public String toString() {
        return "EXCEPTION: Missing flag\n"
                + "Missing flag: " + missingFlag + "\n"
                + "Please include this flag in your input";
    }
}
