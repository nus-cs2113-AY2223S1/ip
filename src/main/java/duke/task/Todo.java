package duke.task;

import duke.exception.EmptyDescriptionException;
import duke.exception.NoSpecificTimeException;
import duke.exception.NoSpecificDeadlineException;


public class  Todo extends Task {

    /**
     * Constructs the Todo task.
     *
     * @param task The task to be store in the list of task.
     * @throws EmptyDescriptionException If task is empty
     * @throws NoSpecificDeadlineException If /by is not specified for deadline task
     * @throws NoSpecificTimeException If /at is not specified for event task.
     */
    public Todo(String task) throws EmptyDescriptionException, NoSpecificDeadlineException, NoSpecificTimeException {
        // Call constructor of superclass and print ou task added
        super(task);
    }


}
