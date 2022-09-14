package Duke.Exceptions;
public class TaskAlreadyMarkedException extends Exception {
    private static final String ERROR_MESSAGE = "This task has already been ";
    private static String markStatus;

    public TaskAlreadyMarkedException(String markStatus) {
        super(ERROR_MESSAGE);
        this.markStatus = markStatus;
    }

    @Override
    public String toString() {
        if (markStatus.equals("marked")) {
            return ERROR_MESSAGE + markStatus + "!";
        } else {
            return ERROR_MESSAGE + markStatus + "!";
        }
    }
}