package duke.exceptions;

public class TaskAlreadyMarkedException extends DukeException {
    public void printTaskAlreadyMarkedError() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The task you specified is already marked!\n" +
                "    ____________________________________________________________";
        System.out.println(error);
    }
}
