package duke.exceptions;

public class TaskAlreadyUnmarkedException extends DukeException {
    public void printTaskAlreadyUnmarkedError() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The task you specified is already unmarked!\n" +
                "    ____________________________________________________________";
        System.out.println(error);
    }
}
