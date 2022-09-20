package duke.exception;

public class DukeInvalidTaskNumberException extends Exception {

    private int taskNum;

    public DukeInvalidTaskNumberException(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public String toString() {
        return "EXCEPTION: Invalid task number\n"
                + "Your task number: " + taskNum + "\n"
                + "Please enter a task number found from the most recent task list displayed";
    }
}
