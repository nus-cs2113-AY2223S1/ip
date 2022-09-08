public class UnrecognisedDeadlineException extends DukeException{
    public UnrecognisedDeadlineException(String message) {
        super(message);
    }
    @Override
    public String getExceptionMessage() {
        return "The input for this deadline is incorrect. Check that you have entered: deadline <description> /by <date>.";
    }
}
