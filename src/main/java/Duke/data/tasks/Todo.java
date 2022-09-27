package Duke.data.tasks;

import Duke.data.exception.DukeException;

/**
 * Subclass of superclass Task
 */
public class Todo extends Task{
    /**
     * Constructor for the Todo class
     * Initialise taskType, description and dueDate variables
     * Throws an exception when there is no or empty input
     * @param userInput is the description of task
     * @throws DukeException
     */
    public Todo(String userInput) throws DukeException {
        super();
        if(userInput.equals(" ")) {
            throw new DukeException();
        }
        super.taskType = "T";
        super.description = userInput;
        super.dueDate = "";
    }
}
