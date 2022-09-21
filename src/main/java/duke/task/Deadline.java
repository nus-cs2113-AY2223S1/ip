package duke.task;

import duke.exception.EmptyDescriptionException;
import duke.exception.NoSpecificTimeException;
import duke.exception.NoSpecificDeadlineException;

public class Deadline extends Task {

    /**
     * Constructs the deadline task.
     *
     * @param task The task to be store in the list of task.
     * @throws EmptyDescriptionException If task is empty
     * @throws NoSpecificDeadlineException If /by is not specified for deadline task
     * @throws NoSpecificTimeException If /at is not specified for event task.
     */
    public Deadline(String task) throws EmptyDescriptionException, NoSpecificDeadlineException, NoSpecificTimeException {
     // Call constructor of super class and change formatting to braces
        super(task.replace("/by", "(by:") + ")");
    }



}
