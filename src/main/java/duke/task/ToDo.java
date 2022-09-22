package duke.task;

import duke.exception.DukeException;

public class ToDo extends Task {

    /**
     * Constructs a ToDo object
     *
     * @param arguments user input containing task name
     * @throws DukeException if task name is empty
     */
    public ToDo(String arguments) throws DukeException {
        super(arguments);
    }

    @Override
    public char taskType() {
        return 'T';
    }

}
