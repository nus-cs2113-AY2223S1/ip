package Duke.Exceptions;

import Duke.Duke;

public class ArguementNotFoundException extends DukeException {
    public void ArgumentNotFoundMessage(){
        System.out.println(
                Duke.PRINT_LINE
                        + "☹ OOPS!!! The description is empty.\n"
                        + Duke.PRINT_LINE
        );
    }

}
