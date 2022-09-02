package duke.exceptions;

public class AccessTaskOutOfBoundsException extends DukeException {
    public void printAccessTaskOutOfBoundsError() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The task you have specified is out of bounds :-(\n";
        System.out.println(error);
    }
}
