package duke.exceptions;

public class MissingTaskNumberException extends DukeException {
    public void printMissingTaskNumberError() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! You did not specify which task to mark/unmark :-(\n" +
                "    ____________________________________________________________";
        System.out.println(error);
    }
}