package duke.exception;

public class DukeInvalidTaskNumberException extends Exception {

    private int taskNum;

    /**
     * Constructor
     * @param taskNum Task number that is out of range
     */
    public DukeInvalidTaskNumberException(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Get the exception message
     * @return String Exception message
     */
    @Override
    public String toString() {
        return "EXCEPTION: Invalid task number\n"
                + "Your task number: " + taskNum + "\n"
                + "Please enter a task number found from the most recent task list displayed";
    }
}
