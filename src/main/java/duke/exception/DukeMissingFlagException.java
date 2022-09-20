package duke.exception;

public class DukeMissingFlagException extends Exception {

    private String missingFlag;

    public DukeMissingFlagException(String missingFlag) {
        this.missingFlag = missingFlag;
    }

    @Override
    public String toString() {
        return "EXCEPTION: Missing flag\n"
                + "Missing flag: " + missingFlag + "\n"
                + "Please include this flag in your input";
    }
}
