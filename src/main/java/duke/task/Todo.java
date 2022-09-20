package duke.task;

import duke.exception.EmptyDescriptionException;
import duke.exception.NoSpecficTimeException;
import duke.exception.NoSpecificDeadlineException;


public class  Todo extends Task {

    public Todo(String task) throws EmptyDescriptionException, NoSpecificDeadlineException, NoSpecficTimeException {
        // Call constructor of superclass and print ou task added
        super(task);
    }


}
