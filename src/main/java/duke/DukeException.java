package duke;

public class DukeException extends Exception{
    private String errorMessage;
    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public void printErrorMessage() {
        System.out.println(errorMessage);
    }
}
