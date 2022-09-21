package duke.task;

import duke.exception.EmptyDescriptionException;
import duke.exception.NoSpecificTimeException;
import duke.exception.NoSpecificDeadlineException;

public class Event extends Task {

    /**
     * Constructs the event task.
     *
     * @param task The task to be store in the list of task.
     * @throws EmptyDescriptionException If task is empty
     * @throws NoSpecificDeadlineException If /by is not specified for deadline task
     * @throws NoSpecificTimeException If /at is not specified for event task.
     */
    public Event(String task) throws EmptyDescriptionException, NoSpecificDeadlineException, NoSpecificTimeException {
        // Call constructor for superclass and change formatting to brackets
        super(task.replace("/at", "(at:") + ")");
    }




}
